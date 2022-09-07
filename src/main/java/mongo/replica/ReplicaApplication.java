package mongo.replica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;

@SpringBootApplication
@Service
public class ReplicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReplicaApplication.class, args);
	}

	@Autowired
	private GridFsTemplate gridFsTemplate;

	@Bean
	CommandLineRunner runner() {

		System.out.println("saveFile try start");
		try {
			saveFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("--------------------------------");
		System.out.println("loadFile try start");

		try {
			downloadFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

//	ファイルの保存
//	addFile()メソッドはファイルをデータベースに保存し、ファイルのオブジェクト ID を返します。
//	追加情報をメタデータとしてファイルに追加できます。例として、追加情報としてファイル サイズを追加しました。しかし、それは必須ではありません。
//	ファイルを保存するには、次のデータを GridFsTemplate の store メソッドに渡す必要があります。
	public String saveFile() throws IOException {

		// 開始タイムスタンプを取得
		long startTime = System.nanoTime();

		// 時間計測をする処理
		// 現在時刻取得サンプルプログラムの現在日時取得部分
		LocalDateTime ldt = LocalDateTime.now();
		ZonedDateTime zdt = ldt.atZone(ZoneOffset.ofHours(+9));
		long epochMilli = zdt.toInstant().toEpochMilli();
		System.out.println("現在時刻 ： " + epochMilli);

		System.out.println("--ディレクトリ内のファイル取得開始--");
		File dir = new File("C:\\mongoDemo");
		processDirectory(dir);
		System.out.println("----ディレクトリ内のファイル取得終了--");
		
		// 終了タイムスタンプを取得
		long endTime = System.nanoTime();
		long result = endTime - startTime;

		System.out.println("---取得結果--------------------");
		System.out.println("開始時刻 ： " + startTime + "ナノ秒");
		System.out.println("終了時刻 ： " + endTime + "ナノ秒");
		System.out.println("処理時間 ： " + result + "ナノ秒");
		System.out.println("処理時間 ： " + (result / 1000) + "マイクロ秒");
		System.out.println("処理時間 ： " + (result / 1000000) + "ミリ秒");

		return "file stored successfully...";
	}

	public void processDirectory(File dir) throws FileNotFoundException {

		System.out.println("--ファイル取得--");

		// ディレクトリのファイルを取得する
		File[] files = dir.listFiles();

		if (files == null) {
			// ファイルがないので処理終了

			System.out.println("--取得終了--");
			return;
		}

		// ファイルを一つずつ処理する
		for (File file : files) {
			if (file.isDirectory()) {
				// 現在のファイルが「ディレクトリ」であればそのディレクトリをさらに処理
				processDirectory(file); // 自分自身を呼び出し
			} else if (file.isFile()) {
				// 現在のファイルが「ファイル」であれば、ファイルに対して実行した処理を行う
				processFile(file);
			}
		}
	}

	private void processFile(File file) throws FileNotFoundException {
		DBObject metadata = new BasicDBObject();
		metadata.put("Orgganization", "Java Techie");

//		System.out.println(f.getName() + " " + f.length() + " bytes");
		InputStream inputStreamCSV = new FileInputStream(file);

		gridFsTemplate.store(inputStreamCSV, "CsvSampleHugeDate.csv", "file/csv", metadata);
//		System.out.println("file id stored :" + FileCSV2);
	}

//  ファイルの取得
//  downloadFile()メソッドは、データベースから特定のファイルを返すために使用されます。
//  GridFSTemplateの findOneメソッドは、クエリに一致するファイルを返します。
//  次に、ステップ 1 で作成した LoadFile クラスから、レスポンスとそのコンテンツを Object に設定して返します。
//  ファイルをバイト配列に設定する必要があります。InputStream をバイト配列に変換するために、Apache Commons IOを使用しています。
//  そのためには、pom.xmlファイルに次の依存関係を追加する必要があります。

	public String downloadFile() throws IOException {
		
		// 開始タイムスタンプを取得
		long startTime = System.nanoTime();
		//
		// 時間計測をする処理
		// 現在時刻取得サンプルプログラムの現在日時取得部分
		LocalDateTime ldt = LocalDateTime.now();
		ZonedDateTime zdt = ldt.atZone(ZoneOffset.ofHours(+9));
		long epochMilli = zdt.toInstant().toEpochMilli();
		System.out.println("現在時刻 ： " + epochMilli);


		//検索したいファイル名
		//fs.fileのfilenameから一致するもの全て取得
		String fileName = "CsvSampleHugeDate.csv";
		GridFSFindIterable gridFsFiles = gridFsTemplate.find(new Query(Criteria.where("filename").is(fileName)));
		
		int count = 0;
		for (GridFSFile gridfile : gridFsFiles) {
//			System.out.println(gridfile.getFilename());
			count++;
		}
		
		// 終了タイムスタンプを取得
		long endTime = System.nanoTime();
		long result = endTime - startTime;
		System.out.println(count + "件");

//		System.out.println(gridFsFile1.toString());
//		System.out.println(gridFsFile2.getChunkSize());

		System.out.println("---取得結果--------------------");
		System.out.println("開始時刻 ： " + startTime + "ナノ秒");
		System.out.println("終了時刻 ： " + endTime + "ナノ秒");
		System.out.println("処理時間 ： " + result + "ナノ秒");
		System.out.println("処理時間 ： " + (result / 1000) + "マイクロ秒");
		System.out.println("処理時間 ： " + (result / 1000000) + "ミリ秒");

		return "ファイルをダウンロードしました";
	}

}
