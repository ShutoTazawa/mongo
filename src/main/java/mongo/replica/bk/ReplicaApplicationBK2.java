package mongo.replica.bk;
//package mongo.replica;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//import java.time.ZonedDateTime;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.gridfs.GridFsOperations;
//import org.springframework.data.mongodb.gridfs.GridFsTemplate;
//import org.springframework.stereotype.Service;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;
//import com.mongodb.client.gridfs.GridFSFindIterable;
//import com.mongodb.client.gridfs.model.GridFSFile;
//
//import mongo.replica.repository.CustomerRepository;
//import mongo.replica.repository.FileRepository;
//
//@SpringBootApplication
//@Service
//public class BK２ {
//
//	public static void main(String[] args) {
//		SpringApplication.run(BK２.class, args);
//	}
//
//	@Autowired
//	private GridFsTemplate gridFsTemplate;
//
//	@Autowired
//	private GridFsOperations gridFsOperations;
//
//	@Autowired
//	private MongoTemplate template;
//
//	@Bean
//	CommandLineRunner runner(CustomerRepository customerRepository, FileRepository fileRepository) {
//
//		// 全削除
////		customerRepository.deleteAll();
//		System.out.println("--------------------------------");
//		System.out.println("saveFile try start");
//		try {
//			saveFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		
//		System.out.println("--------------------------------");
//		System.out.println("loadFile try start");
//		try {
//			downloadFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
////		// 追加
////		Customer customer = new Customer();
////		customer.setFirstName("Alice");
////		customer.setLastName("Smith");
////		customerRepository.insert(customer);
////
////		Customer customer2 = new Customer();
////		customer2.setFirstName("Bob");
////		customer2.setLastName("Smith");
////		customerRepository.insert(customer2);
//
//		System.out.println("--------------------------------");
//		System.out.println("File処理を開始します");
//
////		FileEntitye File1 = new FileEntitye();
////		File1.setFilename("hoge1file");
////		File1.setFileSize("100");
////		File1.setFileType("json");
////		String[] str = { "Apple", "Orange", "Melon" };
////		File1.setFile(str);
////		fileRepository.insert(File1);
////
////		System.out.println("--------------------------------");
////		System.out.println("Customer処理");
////		// 全件検索
//////		for (Customer cus : customerRepository.findAll()) {
//////			System.out.println(cus);
//////		}
//
//		return null;
//
//	}
////	ファイルの保存
////	addFile()メソッドはファイルをデータベースに保存し、ファイルのオブジェクト ID を返します。
////	追加情報をメタデータとしてファイルに追加できます。例として、追加情報としてファイル サイズを追加しました。しかし、それは必須ではありません。
////	ファイルを保存するには、次のデータを GridFsTemplate の store メソッドに渡す必要があります。
//
//	public String saveFile() throws IOException {
//		
//		//開始タイムスタンプを取得
//		long startTime = System.nanoTime();
//		//
//		//時間計測をする処理
//		//現在時刻取得サンプルプログラムの現在日時取得部分
//		LocalDateTime ldt = LocalDateTime.now();
//		ZonedDateTime zdt = ldt.atZone(ZoneOffset.ofHours(+9));
//		long epochMilli = zdt.toInstant().toEpochMilli();
//		System.out.println("現在時刻 ： " + epochMilli);
//		//
//		//終了タイムスタンプを取得
//		long endTime = System.nanoTime();
//		long result = endTime - startTime;
////		DBObject metadata = new BasicDBObject();
////		metadata.put("Orgganization", "Java Techie");
////		InputStream inputStreamPng = new FileInputStream("C:\\mongoDemo/logo.png");
////		InputStream inputStreamCSV = new FileInputStream("C:\\mongoDemo/CsvSampleHugeDate.csv");
////		InputStream mongoHugeInputStream = new FileInputStream("C:\\mongoDemo/");
//
//		System.out.println("--取得開始--");
//		File dir = new File("C:\\mongoDemo");
//		processDirectory(dir);
//		System.out.println("--取得終了--");
//
////		metadata.put("type", "image");
//
////		Object fileId = operations.store(inputStream, "logo.png", "image/png", metadata).toString();
////		Object fileIDPng = gridFsOperations.store(inputStreamPng, "logo3.png", "image/png", metadata);
////		Object FileCSVCSV = gridFsOperations.store(inputStreamCSV, "CsvSampleHugeDate.csv", "file/csv", metadata);
//
////		System.out.println("file id stored :" + fileIDPng);
////		System.out.println("file id stored :" + FileCSVCSV);
////		metadata.put("type", "data");
////		gridFsOperations.store(new FileInputStream(""), "myText.txt", "text/plain", metadata);
//
//		System.out.println("---取得結果--------------------");
//		System.out.println("開始時刻 ： " + startTime + "ナノ秒");
//		System.out.println("終了時刻 ： " + endTime + "ナノ秒");
//		System.out.println("処理時間 ： " + result + "ナノ秒");
//		System.out.println("処理時間 ： " + (result / 1000) + "マイクロ秒");
//		System.out.println("処理時間 ： " + (result / 1000000) + "ミリ秒");
//
//		
//		return "file stored successfully...";
//	}
//
////    ファイルの取得
////    downloadFile()メソッドは、データベースから特定のファイルを返すために使用されます。
////    GridFSTemplateの findOneメソッドは、クエリに一致するファイルを返します。
////    次に、ステップ 1 で作成した LoadFile クラスから、レスポンスとそのコンテンツを Object に設定して返します。
////    ファイルをバイト配列に設定する必要があります。InputStream をバイト配列に変換するために、Apache Commons IOを使用しています。
////    そのためには、pom.xmlファイルに次の依存関係を追加する必要があります。
//
//	public void processDirectory(File dir) throws FileNotFoundException {
//
//		System.out.println("--ファイル取得--");
//
//		// ディレクトリのファイルを取得する
//		File[] files = dir.listFiles();
//
//		if (files == null) {
//			// ファイルがないので処理終了
//
//			System.out.println("--取得終了--");
//			return;
//		}
//
//		// ファイルを一つずつ処理する
//		for (File f : files) {
//			if (f.isDirectory()) {
//				// 現在のファイルが「ディレクトリ」であればそのディレクトリをさらに処理
//				processDirectory(f); // 自分自身を呼び出し
//			} else if (f.isFile()) {
//				// 現在のファイルが「ファイル」であれば、ファイルに対して実行した処理を行う
//				processFile(f);
//			}
//		}
//	}
//
//	private void processFile(File f) throws FileNotFoundException {
//		DBObject metadata = new BasicDBObject();
//		metadata.put("Orgganization", "Java Techie");
//
//		System.out.println(f.getName() + " " + f.length() + " bytes");
//		InputStream inputStreamCSV = new FileInputStream(f);
//
//		Object FileCSV2 = gridFsOperations.store(inputStreamCSV, "CsvSampleHugeDate.csv", "file/csv", metadata);
////		System.out.println("file id stored :" + FileCSV2);
//	}
//
//	public String downloadFile() throws IOException {
//		//開始タイムスタンプを取得
//		long startTime = System.nanoTime();
//		//
//		//時間計測をする処理
//		//現在時刻取得サンプルプログラムの現在日時取得部分
//		LocalDateTime ldt = LocalDateTime.now();
//		ZonedDateTime zdt = ldt.atZone(ZoneOffset.ofHours(+9));
//		long epochMilli = zdt.toInstant().toEpochMilli();
//		System.out.println("現在時刻 ： " + epochMilli);
//		//
//		//終了タイムスタンプを取得
//		long endTime = System.nanoTime();
//		long result = endTime - startTime;
//
//		String id = "CsvSampleHugeDate.csv";
//		GridFSFindIterable gridFsFile1 = gridFsTemplate.find(new Query(Criteria.where("filename").is(id)));
//		GridFSFile gridFsFile2 = gridFsTemplate.findOne(new Query(Criteria.where("filename").is(id)));
//
//		for (GridFSFile gridfile : gridFsFile1) {
//			System.out.println(gridfile.getFilename());
//		}
//
//		System.out.println(gridFsFile1.toString());
//		System.out.println(gridFsFile2.getChunkSize());
//
//		System.out.println("---取得結果--------------------");
//		System.out.println("開始時刻 ： " + startTime + "ナノ秒");
//		System.out.println("終了時刻 ： " + endTime + "ナノ秒");
//		System.out.println("処理時間 ： " + result + "ナノ秒");
//		System.out.println("処理時間 ： " + (result / 1000) + "マイクロ秒");
//		System.out.println("処理時間 ： " + (result / 1000000) + "ミリ秒");
//		return "ファイルをダウンロードしました";
//	}
//
//}
