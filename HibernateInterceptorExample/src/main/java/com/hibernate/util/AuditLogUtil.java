package com.hibernate.util;

import java.util.Date;

import org.hibernate.Session;

import com.hibernate.interceptor.IAuditLog;
import com.hibernate.model.AuditLog;

public class AuditLogUtil {

	public static void LogIt(String action, IAuditLog entity,
			Session tempSession) {

		// Session tempSession =
		// HibernateUtil.getSessionFactory().openSession();

		try {
			AuditLog auditRecord = new AuditLog(action, entity.getLogDeatil(),
					new Date(), entity.getId(), entity.getClass().toString());
			
			tempSession.save(auditRecord);
			//tempSession.flush();

		} finally {
			//tempSession.close();

		}

	}
}