/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watsonhealth.fhir.search.test.tools;

import static com.ibm.watsonhealth.fhir.model.path.util.FHIRPathUtil.eval;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ibm.watsonhealth.fhir.exception.FHIRException;
import com.ibm.watsonhealth.fhir.model.format.Format;
import com.ibm.watsonhealth.fhir.model.generator.FHIRGenerator;
import com.ibm.watsonhealth.fhir.model.generator.exception.FHIRGeneratorException;
import com.ibm.watsonhealth.fhir.model.path.FHIRPathElementNode;
import com.ibm.watsonhealth.fhir.model.path.FHIRPathNode;
import com.ibm.watsonhealth.fhir.model.path.FHIRPathTree;
import com.ibm.watsonhealth.fhir.model.resource.Bundle;
import com.ibm.watsonhealth.fhir.model.resource.SearchParameter;
import com.ibm.watsonhealth.fhir.model.type.BundleType;
import com.ibm.watsonhealth.fhir.model.util.FHIRUtil;
import com.ibm.watsonhealth.fhir.search.SearchConstants;
import com.ibm.watsonhealth.fhir.search.parameters.ParametersUtil;

/**
 * 
 * @author pbastide
 *
 */
public class IndexCalculationTool {

    public static final String FHIR_PATH = "Bundle.entry.resource.expression";

    /*
     * used in test to make the compiled code accessible.
     */
    static {
        System.setProperty("javax.xml.accessExternalSchema", "file");
    }

    /**
     * @param args
     * @throws FHIRGeneratorException
     * @throws IOException
     */
    public static void main(String[] args) throws FHIRGeneratorException, IOException {
        try (InputStream stream = ParametersUtil.class.getClassLoader().getResourceAsStream(ParametersUtil.FHIR_DEFAULT_SEARCH_PARAMETERS_FILE)) {
            if (stream == null) {
                System.out.println(String.format(ParametersUtil.ERROR_EXCEPTION, ParametersUtil.FHIR_DEFAULT_SEARCH_PARAMETERS_FILE));
            }
            Set<String> sets = process(stream);
            process(sets);
        }

        // Output to Standard Out
        java.io.PrintStream out = System.out;

    }

    /**
     * simple output method.
     * 
     * @param params
     * @param out
     * @throws FHIRGeneratorException
     */
    private static void output(List<SearchParameter> params, java.io.PrintStream out) throws FHIRGeneratorException {
        Bundle.Builder build = Bundle.builder(BundleType.COLLECTION);
        for (SearchParameter param : params) {
            build.entry(Bundle.Entry.builder().resource(param).fullUrl(param.getUrl()).build());

        }

        Bundle bundle = build.build();
        FHIRGenerator.generator(Format.JSON, true).generate(bundle, out);
        out.println(bundle.toString());
    }

    /*
     * processes the search parameters expressions split into an Set.
     */
    private static Set<String> process(InputStream stream) {
        Set<String> searchExpressions = new HashSet<>();

        try {
            // The code is agnostic to format.
            Bundle bundle = FHIRUtil.read(Bundle.class, Format.JSON, new InputStreamReader(stream));

            FHIRPathTree tree = FHIRPathTree.tree(bundle);
            Collection<FHIRPathNode> result = eval(FHIR_PATH, tree.getRoot());

            int count = 0;
            for (FHIRPathElementNode node : result.stream().map(a -> a.as(FHIRPathElementNode.class)).collect(Collectors.toList())) {
                String[] exprs = node.getValue().asStringValue().string().split("\\" + SearchConstants.PARAMETER_DELIMITER);
                for(String expr : exprs) {
                    searchExpressions.add(expr.trim());
                    count++;
                }
                
            }
            System.out.println("Total Expressions: " + count);
            System.out.println("Total Unique Expressions: " + searchExpressions.size());
        } catch (FHIRException fe) {
            // This exception is highly unlikely, but still possible.
            System.out.println(String.format(ParametersUtil.ERROR_EXCEPTION, ParametersUtil.FROM_STEAM));
        }

        return Collections.unmodifiableSet(searchExpressions);
    }

    private static void process(Set<String> uniques) {
        
        List<String> sorted = uniques.stream().sorted().collect(Collectors.toList());
        
        for(String uniq : sorted) {
            System.out.println(uniq);
        }
    }
    
}