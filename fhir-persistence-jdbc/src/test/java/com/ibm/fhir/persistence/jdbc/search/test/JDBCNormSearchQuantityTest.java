/*
 * (C) Copyright IBM Corp. 2018,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.fhir.persistence.jdbc.search.test;

import java.util.Properties;

import com.ibm.fhir.persistence.FHIRPersistence;
import com.ibm.fhir.persistence.jdbc.impl.FHIRPersistenceJDBCNormalizedImpl;
import com.ibm.fhir.persistence.jdbc.test.util.DerbyInitializer;
import com.ibm.fhir.persistence.search.test.AbstractSearchQuantityTest;


public class JDBCNormSearchQuantityTest extends AbstractSearchQuantityTest {

    private Properties testProps;

    public JDBCNormSearchQuantityTest() throws Exception {
        this.testProps = readTestProperties("test.normalized.properties");
    }

    @Override
    public void bootstrapDatabase() throws Exception {
        DerbyInitializer derbyInit;
        String dbDriverName = this.testProps.getProperty("dbDriverName");
        if (dbDriverName != null && dbDriverName.contains("derby")) {
            derbyInit = new DerbyInitializer(this.testProps);
            derbyInit.bootstrapDb();
        }
    }
    
    @Override
    public FHIRPersistence getPersistenceImpl() throws Exception {
        return new FHIRPersistenceJDBCNormalizedImpl(this.testProps);
    }
}
