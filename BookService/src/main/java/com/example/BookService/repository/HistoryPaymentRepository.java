package com.example.BookService.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.BookService.dto.HistoryPaymentEbookDto;
import com.example.BookService.entity.HistoryPayment;

public interface HistoryPaymentRepository extends JpaRepository<HistoryPayment, Long>{
	HistoryPayment findByUserIdAndBookId(@Param("userId") Long userId,@Param("bookId") Long bookId );
	Page<HistoryPayment> findByUserId(@Param("userId") Long userId, Pageable pageable);
	@Query(value = "select h.history_payment_id, h.code_payment, h.book_id, h.content_payment, h.money_payment"
			+ ", h.created_date, h.term_id, h.user_id, b.book_name "
			+ "from e_book.book b inner join book.history_payment h where b.id = h.book_id "
			+ "and h.book_id is not null "
			+ "and h.user_id = :userId ", nativeQuery = true)
	List<Object[]> findHisPaymentBook(@Param("userId") Long userId);
	
	@Query(value = "select h.history_payment_id, h.code_payment, h.audio_book_id, h.content_payment, h.money_payment"
			+ ", h.created_date, h.term_id, h.user_id, ab.audio_book_name "
			+ "from podcast.audio_book ab inner join book.history_payment h where ab.id = h.audio_book_id "
			+ "and h.audio_book_id is not null "
			+ "and h.user_id = :userId ", nativeQuery = true)
	List<Object[]> findHisPaymentAudioBook(@Param("userId") Long userId);
	
	@Query(value = "select h.history_payment_id, h.code_payment, h.book_id, h.content_payment, h.money_payment"
			+ ", h.created_date, h.term_id, h.user_id "
			+ "from book.history_payment h "
			+ "where h.term_id is not null "
			+ "and h.user_id = :userId ", nativeQuery = true)
	List<Object[]> findHisPaymentRegiscard(@Param("userId") Long userId);
	
	@Query(value = "select h.history_payment_id as id, h.code_payment as codePayment, h.content_payment as contentPayment, h.money_payment as moneyPayment, "
            + "h.created_date as createdDate, b.book_name as bookName, b.book_category as bookCategory, u.user_name as userName "
            + "from e_book.book b inner join book.history_payment h on b.id = h.book_id "
            + "inner join security_layer.user u on h.user_id = u.id "
            + "where h.book_id is not null "
            + "and (:bookName is null or upper(b.book_name) like concat('%', upper(:bookName), '%')) "
            + "and (:bookCategory is null or upper(b.book_category) like concat('%', upper(:bookCategory), '%')) "
            + "and (:userName is null or upper(u.user_name) like concat('%', upper(:userName), '%')) "
            + "and b.book_type = 3 "
            + "and h.created_date between STR_TO_DATE(:fromDate, '%d/%m/%Y') and DATE_ADD(STR_TO_DATE(:toDate, '%d/%m/%Y'), INTERVAL 1 DAY) ", nativeQuery = true)
	List<Object[]> searchPaymentEbook(@Param("bookName") String bookName,
                                                @Param("bookCategory") String bookCategory,
                                                @Param("userName") String userName,
                                                @Param("fromDate") String fromDate,
                                                @Param("toDate") String toDate);
	
	@Query(value = "select h.history_payment_id as id, h.code_payment as codePayment, h.content_payment as contentPayment, h.money_payment as moneyPayment, "
            + "h.created_date as createdDate, ab.audio_book_name as bookName, ab.audio_book_category as bookCategory, u.user_name as userName "
            + "from podcast.audio_book ab inner join book.history_payment h on ab.id = h.audio_book_id "
            + "inner join security_layer.user u on h.user_id = u.id "
            + "where h.audio_book_id is not null "
            + "and (:audioBookName is null or upper(ab.audio_book_name) like concat('%', upper(:audioBookName), '%')) "
            + "and (:audioBookCategory is null or upper(ab.audio_book_category) like concat('%', upper(:audioBookCategory), '%')) "
            + "and (:userName is null or upper(u.user_name) like concat('%', upper(:userName), '%')) "
            + "and ab.audio_book_type = 3 "
            + "and h.created_date between STR_TO_DATE(:fromDate, '%d/%m/%Y') and DATE_ADD(STR_TO_DATE(:toDate, '%d/%m/%Y'), INTERVAL 1 DAY)", nativeQuery = true)
	List<Object[]> searchPaymentAudioBook(@Param("audioBookName") String audioBookName,
                                                @Param("audioBookCategory") String audioBookCategory,
                                                @Param("userName") String userName,
                                                @Param("fromDate") String fromDate,
                                                @Param("toDate") String toDate);
	
	@Query(value = "select h.code_payment, u.user_name, t.term, h.money_payment, h.created_date, h.content_payment, mb.start_date, mb.end_date "
            + "from book.history_payment h inner join book.member_manager mb on h.user_id = mb.user_id "
            + "inner join book.term t on t.term_id = mb.term_id "
            + "inner join security_layer.user u on h.user_id = u.id "
            + "where h.term_id is not null "
            + "and (:term is null or t.term = :term) "
            + "and (:userName is null or upper(u.user_name) like concat('%', upper(:userName), '%')) "
            + "and h.created_date between STR_TO_DATE(:fromDate, '%d/%m/%Y') and DATE_ADD(STR_TO_DATE(:toDate, '%d/%m/%Y'), INTERVAL 1 DAY) ", nativeQuery = true)
	List<Object[]> searchPaymentMember(@Param("term") Long term,
                                                @Param("userName") String userName,
                                                @Param("fromDate") String fromDate,
                                                @Param("toDate") String toDate);
	@Query(value = "select count(*) from e_book.book ", nativeQuery = true)
	Long getTotalReadingBoook();
	
	@Query(value = "select count(*) from podcast.audio_book ", nativeQuery = true)
	Long getTotalReadingAudioBook();
	
	@Query(value = "SELECT count(*) "
			+ "FROM book.history_payment h "
			+ "WHERE MONTH(h.created_date) = MONTH(CURRENT_DATE()) "
			+ "AND YEAR(h.created_date) = YEAR(CURRENT_DATE()) ", nativeQuery = true)
	Long getTotalPaymentInMonth();
	
	@Query(value = "SELECT sum(h.money_payment) "
			+ "FROM book.history_payment h "
			+ "WHERE MONTH(h.created_date) = MONTH(CURRENT_DATE()) "
			+ "AND YEAR(h.created_date) = YEAR(CURRENT_DATE()) ", nativeQuery = true)
	Long getTotalMoneyPaymentInMonth();
	
	@Query(value = "select count(*) from book.member_manager ", nativeQuery = true)
	Long getTotalMember();
	
	@Query(value = "select count(*) from book.member_manager m "
			+ "WHERE MONTH(m.created_date) = MONTH(CURRENT_DATE()) "
			+ "AND YEAR(m.created_date) = YEAR(CURRENT_DATE()) ", nativeQuery = true)
	Long getTotalMemberRegisInMonth();
}
