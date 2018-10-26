package com.hotent.core.db;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class RollbackJdbcTemplate {

	@Resource
	private TransactionTemplate txTemplate;

	public Object executeRollBack(final IRollBack rollBack, final String script,final Map<String, Object> map) {
		return txTemplate.execute(new TransactionCallback<Object>() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				try {
					return rollBack.execute(script, map);
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				} finally {
					status.setRollbackOnly();
				}
			}
		});
	}

}
