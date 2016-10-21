package com.lockbur.book.service.impl.bean;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lockbur.book.gateway.service.LoaderCofig;
/**
 *
 * @author Administrator
 */
@Service
public class LoaderConfigBean implements LoaderCofig {

    Logger logger = LoggerFactory.getLogger(LoaderConfigBean.class);

    @Override
    public void load() {
        logger.info("LoaderConfigBean test");
    }

}
