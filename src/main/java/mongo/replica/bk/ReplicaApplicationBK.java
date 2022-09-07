package mongo.replica.bk;
//package mongo.replica;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
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
//import com.mongodb.client.gridfs.model.GridFSFile;
//
//import mongo.replica.entities.Customer;
//import mongo.replica.entities.File;
//import mongo.replica.repository.CustomerRepository;
//import mongo.replica.repository.FileRepository;
//
//@SpringBootApplication
//@Service
//public class BK {
//
//	public static void main(String[] args) {
//		SpringApplication.run(BK.class, args);
//	}
//
//	private GridFsTemplate gridFsTemplate;
//
//	@Autowired
//	private GridFsOperations gridFsOperations;
//
//	private MongoTemplate template;
//
//	@Bean
//	CommandLineRunner runner(CustomerRepository customerRepository, FileRepository fileRepository) {
//
//		// 全削除
////		customerRepository.deleteAll();
//
//		System.out.println("saveFile try start");
//		try {
//			saveFile();
//		} catch (IOException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
//
//		// 追加
//		Customer customer = new Customer();
//		customer.setFirstName("Alice");
//		customer.setLastName("Smith");
//		customerRepository.insert(customer);
//
//		Customer customer2 = new Customer();
//		customer2.setFirstName("Bob");
//		customer2.setLastName("Smith");
//		customerRepository.insert(customer2);
//
//		System.out.println("--------------------------------");
//		System.out.println("File処理を開始します");
//
//		File File1 = new File();
//		File1.setFilename("hoge1file");
//		File1.setFileSize("100");
//		File1.setFileType("json");
//		String[] str = { "Apple", "Orange", "Melon" };
//		File1.setFile(str);
//		fileRepository.insert(File1);
//
//		System.out.println("--------------------------------");
//		System.out.println("Customer処理");
//		// 全件検索
//		for (Customer cus : customerRepository.findAll()) {
//			System.out.println(cus);
//		}
//
//		return null;
//
//	}
//
////	@Bean
////	CommandLineRunner runner(FileRepository repository) {
////
//////		System.out.println("saveFile try start");
//////		try {
//////			saveFile();
//////		} catch (IOException e) {
//////			e.printStackTrace();
////		}
////
////		// 追加
////		LoadFile loadFile = new LoadFile();
////		loadFile.setFilename("hoge-file");
////		loadFile.setFileType("csv");
////		repository.insert(loadFile);
////
////		LoadFile loadFile2 = new LoadFile();
////		loadFile.setFilename("hoge-file");
////		loadFile.setFileType("csv");
////		repository.insert(loadFile);
////
//////		// 全件検索
//////		for (LoadFile faile : repository.findAll()) {
//////			System.out.println(faile);
//////		}
////		
////		repository.count();
////		
////		return null;
////
////	}
//
////	ファイルの保存
////	addFile()メソッドはファイルをデータベースに保存し、ファイルのオブジェクト ID を返します。
////	追加情報をメタデータとしてファイルに追加できます。例として、追加情報としてファイル サイズを追加しました。しかし、それは必須ではありません。
////	ファイルを保存するには、次のデータを GridFsTemplate の store メソッドに渡す必要があります。
//
//	public String saveFile() throws IOException {
//
//		DBObject metadata = new BasicDBObject();
//		metadata.put("Orgganization", "Java Techie");
//
//		InputStream inputStream = new FileInputStream("C:\\mongoDemo/logo.png");
//		metadata.put("type", "image");
//
////		Object fileId = operations.store(inputStream, "logo.png", "image/png", metadata).toString();
//		Object fileID = gridFsOperations.store(inputStream, "logo3.png", "image/png", metadata);
//		System.out.println("file id stored :" + fileID);
//
////		metadata.put("type", "data");
////		gridFsOperations.store(new FileInputStream(""), "myText.txt", "text/plain", metadata);
//
//		return "file stored successfully...";
//	}
//
//////	ファイルの保存
//////	addFile()メソッドはファイルをデータベースに保存し、ファイルのオブジェクト ID を返します。
//////	追加情報をメタデータとしてファイルに追加できます。例として、追加情報としてファイル サイズを追加しました。しかし、それは必須ではありません。
//////	ファイルを保存するには、次のデータを GridFsTemplate の store メソッドに渡す必要があります。
////
////	public String saveFile(MultipartFile upload) throws IOException {
////
////		DBObject metadata = new BasicDBObject();
////		metadata.put("fileSize", upload.getSize());
////		
////		InputStream inputStream = new FileInputStream("C:\\Users\\田澤柊人\\Desktop");
////		
////		metadata.put("type", "image");
//////		Object fileId = operations.store(inputStream, "logo.png", "image/png", metadata).toString();
////		Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(),
////				metadata);
////		
////		System.out.println("file id stored :" + fileID);
////		
////		metadata.put("type","data");
////		operations.store(new FileInputStream(""),"MyText.txt","text/plain",metadata);
////
////		return "file stored successfully...";
////	}
//
////    ファイルの取得
////    downloadFile()メソッドは、データベースから特定のファイルを返すために使用されます。
////    GridFSTemplateの findOneメソッドは、クエリに一致するファイルを返します。
////    次に、ステップ 1 で作成した LoadFile クラスから、レスポンスとそのコンテンツを Object に設定して返します。
////    ファイルをバイト配列に設定する必要があります。InputStream をバイト配列に変換するために、Apache Commons IOを使用しています。
////    そのためには、pom.xmlファイルに次の依存関係を追加する必要があります。
//
//	public String downloadFile(String id) throws IOException {
//
//		GridFSFile dbFile = gridFsOperations.findOne(new Query(Criteria.where("_id").is(id)));
//
//		File loadFile = new File();
//
////		if (dbFile != null && dbFile.getMetadata() != null) {
////			loadFile.setFilename(dbFile.getFilename());
////
////			loadFile.setFileType(dbFile.getMetadata().get("_contentType").toString());
////
////			loadFile.setFileSize(dbFile.getMetadata().get("fileSize").toString());
////
////			loadFile.setFile(IOUtils.toByteArray(gridFsOperations.getResource(dbFile).getInputStream()));
////		}
//
//		return "ファイルをダウンロードしました" + loadFile.getFilename();
//	}
//
//}
