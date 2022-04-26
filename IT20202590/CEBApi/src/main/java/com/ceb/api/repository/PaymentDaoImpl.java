package com.ceb.api.repository;

import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ceb.api.entity.Customer;

@Repository
public class PaymentDaoImpl implements PaymentDao {
	private static final Logger LOG = LoggerFactory.getLogger(PaymentDaoImpl.class);

	// Define Create SQL Queries
	private static final String CREATE_PAYMENT_SQL = "INSERT INTO payment(pay_val,create_date,create_by,state,fk_cus_num) "
			+ "VALUES(:pay_val,:create_date,:create_by,:state,:fk_cus_num)";

	// Define Payment Update SQL
	private static final String UPDATE_PAYMENT_SQL = "UPDATE payment SET pay_val=:pay_val WHERE pay_id=:pay_id";

	// Define Payment Delete SQL
	private static final String DELETE_PAYMENT_SQL = "DELETE FROM payment WHERE pay_id=?\r\n" + "";

	// Define Select Customer SQL
	private static final String GET_CUSTOMER_BY_NIC_SQL = "SELECT cus_num, name, nic, email, phone FROM customer WHERE nic=?";

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public PaymentDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int createNewPayment(Customer customer, BigDecimal paymentValue)
			throws SocketTimeoutException, DataAccessException, Exception {
		LOG.info("<------ Execute Create New Payment In Payment Repository -------->");

		int createstate = 0;

		Map<String, Object> paraMap = new HashMap<String, Object>();

		paraMap.put("pay_val", paymentValue);
		paraMap.put("create_date", new Date());
		// TODO: Fetch From SecurityContextHolder
		paraMap.put("create_by", "abc");
		paraMap.put("state", (short) 1);
		paraMap.put("fk_cus_num", customer.getCustomerID());

		createstate = this.namedParameterJdbcTemplate.update(CREATE_PAYMENT_SQL, paraMap);

		LOG.info("<---------- Successfuly Create New Payment ------------>");

		return createstate;

	}

	@Override
	public int updatePayment(Integer paymentID, BigDecimal newPayValue)
			throws SocketTimeoutException, DataAccessException, Exception {
		LOG.info("<------ Execute Update  In Payment Repository Payment ID:{} - New Value:{}-------->", paymentID,
				newPayValue);
		int updatestate = 0;

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("pay_val", newPayValue);
		paraMap.put("pay_id", paymentID);

		updatestate = this.namedParameterJdbcTemplate.update(UPDATE_PAYMENT_SQL, paraMap);

		LOG.info("<---------- Successfuly Update Payment ------------>");

		return updatestate;

	}

	@Override
	public int cancelPayment(Integer paymentNumber) throws SocketTimeoutException, DataAccessException, Exception {
		LOG.info("<------ Execute Cancel Payment In Payment Repository Payment ID:{} -------->", paymentNumber);
		return this.jdbcTemplate.update(DELETE_PAYMENT_SQL, paymentNumber);
	}

	@Override
	public Customer getCustomerByNic(String nic)
			throws SocketTimeoutException, EmptyResultDataAccessException, DataAccessException, Exception {
		LOG.info("<------ Execute Get Customer By NIC:{} In Payment Repository -------->", nic);
		return this.jdbcTemplate.queryForObject(GET_CUSTOMER_BY_NIC_SQL, new RowMapper<Customer>() {

			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Customer customer = new Customer(rs.getInt("cus_num"), rs.getString("name"), rs.getString("nic"),
						rs.getString("email"), rs.getString("phone"));
				return customer;
			}
		}, nic);
	}

}
