package com.test.elastic;

import com.test.elastic.model.Article;
import com.test.elastic.model.Book;
import com.test.elastic.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot13ElasticApplicationTests {

	@Autowired
	JestClient jestClient;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	@Test
	public void testSpringDataRepositoryPut(){
		Book book = new Book();
		book.setId(1);
		book.setBookName("西游记");
		book.setAuthor("吴承恩");

		bookRepository.index(book);
	}

	@Test
	public void testSpringDataRepositoryGet(){
		List<Book> list = bookRepository.findByBookNameLike("游");

		for (Book book : list) {
			System.out.println(book);
		}
	}

	@Test
	public void testSpringDataTemplatePut(){
		Book book = new Book();
		book.setId(1);
		book.setBookName("西游记");
		book.setAuthor("吴承恩");

		Pageable pageable = new PageRequest(1, 5);
		String word = "";
//		SearchQuery searchQuery = new NativeSearchQueryBuilder()
//				.withQuery(queryStringQuery(word))
//				.withFilter(boolFilter().must(termFilter("id", documentId)))
//				.withPageable(pageable)
//				.build();
//		elasticsearchTemplate.queryForList(searchQuery, Book.class);
	}

	@Test
	public void testSpringDataTemplateGet(){
		List<Book> list = bookRepository.findByBookNameLike("游");
		for (Book book : list) {
			System.out.println(book);
		}
	}

	@Test
	public void testJestPut() {
		//1、给Es中索引（保存）一个文档；
		Article article = new Article();
		article.setId(1);
		article.setTitle("好消息");
		article.setAuthor("zhangsan");
		article.setContent("Hello World");

		//构建索引功能
		Index index = new Index.Builder(article).index("bfh").type("news").build();

		//执行
		try {
			jestClient.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testJestGet(){
		//查询表达式
		String json ="{\n" +
				"    \"query\" : {\n" +
				"        \"match\" : {\n" +
				"            \"content\" : \"hello\"\n" +
				"        }\n" +
				"    }\n" +
				"}";

		//构建搜索功能
		Search search = new Search.Builder(json).addIndex("bfh").addType("news").build();

		//执行
		try {
			SearchResult result = jestClient.execute(search);
			System.out.println(result.getJsonString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

