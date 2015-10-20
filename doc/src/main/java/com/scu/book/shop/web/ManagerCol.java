package com.scu.book.shop.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scu.book.shop.entity.Book;
import com.scu.book.shop.service.BookService;

/**
 * task col
 *  
 * @author lynch
 */
@Controller
@RequestMapping(value="/manager")
public class ManagerCol {
	
	@Autowired
	private BookService bookService;
	
	/**
	 * 后台首页
	 */	
	@RequestMapping(value = "index")
	public String index() {
		return "manager/index";
	}
	/**
	 * 查询所有书
	 * @return
	 */
	@RequestMapping(value = "books")
	public String books() {
		return "manager/bookview";
	}
	@RequestMapping(value = "booksJson")
	@ResponseBody
	public Map<String, Object> booksJson(HttpServletRequest request,Integer rows, Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Page<Book> books  = bookService.list(new PageRequest(page - 1, rows, Sort.Direction.DESC, "bookId"));
		map.put("total", books.getTotalElements());
		map.put("rows", books.getContent());
		return map;
	}
	/**
	 * 添加
	 * @return
	 */
	@RequestMapping(value="bookAdd")
	@ResponseBody
	public Map<String, Object> add(Book book){
		Map<String, Object> map = new HashMap<String, Object>();
		book.setBookComments("2");
		book.setBookDetails("2");
		book.setBookPictures("2");
		book.setBookPoint(2);
		book.setBookPress("3");
		book.setBookPrice((double)13);
		book.setBookPublishTime(new Date());
		book.setBookRetailprice((double)12.0);
		book.setBookStoreamont(3);
		book.setBuyinglimitiAmount(3);
		book.setIfOnSale(true);
		book.setOnSaleTime(new Date());
		bookService.save(book);
		map.put("success", true);
		return map;
	}
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value="bookDel")
	@ResponseBody
	public Map<String, Object> del(Integer bookId){
		Map<String, Object> map = new HashMap<String, Object>();
		bookService.delete(bookId);
		map.put("success", true);
		return map;
	}
	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(value="bookEdit")
	@ResponseBody
	public Map<String, Object> edit(Book book){
		Map<String, Object> map = new HashMap<String, Object>();
		
		Book book2 = bookService.get(book.getBookId());
		book2.setBookAuthorname(book.getBookAuthorname());
		book2.setBookName(book.getBookName());
		bookService.save(book2);
		map.put("success", true);
		return map;
	}
}
