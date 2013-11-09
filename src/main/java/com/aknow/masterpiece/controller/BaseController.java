package com.aknow.masterpiece.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.aknow.masterpiece.util.UtilityMethods;

public abstract class BaseController extends Controller {

	private static Log log = LogFactory.getLog(BaseController.class);

	@Override
	protected Navigation run() throws Exception {
		log.info(this.getClass().getName() + " start.");
		
		Navigation n;
		
		try {
			 n = runImpl();
		} catch (Exception e) {
            throw UtilityMethods.sendErrorMail(this.getClass().getName(), e);
		}

		log.info(this.getClass().getName() + " end.");
		
		return n;
	}

	protected abstract Navigation runImpl() throws Exception;
}
