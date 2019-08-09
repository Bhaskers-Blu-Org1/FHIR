/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watsonhealth.fhir.model.patch.test;

import static com.ibm.watsonhealth.fhir.model.type.String.string;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ibm.watsonhealth.fhir.model.format.Format;
import com.ibm.watsonhealth.fhir.model.generator.FHIRGenerator;
import com.ibm.watsonhealth.fhir.model.generator.exception.FHIRGeneratorException;
import com.ibm.watsonhealth.fhir.model.parser.FHIRJsonParser;
import com.ibm.watsonhealth.fhir.model.parser.FHIRParser;
import com.ibm.watsonhealth.fhir.model.parser.exception.FHIRParserException;
import com.ibm.watsonhealth.fhir.model.patch.FHIRJsonPatch;
import com.ibm.watsonhealth.fhir.model.patch.exception.FHIRPatchException;
import com.ibm.watsonhealth.fhir.model.resource.Patient;
import com.ibm.watsonhealth.fhir.model.resource.Resource;
import com.ibm.watsonhealth.fhir.model.type.Boolean;
import com.ibm.watsonhealth.fhir.model.type.Date;
import com.ibm.watsonhealth.fhir.model.type.HumanName;
import com.ibm.watsonhealth.fhir.model.type.Id;
import com.ibm.watsonhealth.fhir.model.type.Instant;
import com.ibm.watsonhealth.fhir.model.type.Meta;
import com.ibm.watsonhealth.fhir.model.type.Narrative;
import com.ibm.watsonhealth.fhir.model.type.NarrativeStatus;

public class FHIRJsonPatchTest {        
    @BeforeClass
    public void setUp() {
    }
    
    @Test
    public void testAddOperation() throws FHIRGeneratorException, FHIRPatchException {
        Patient patient = buildPatient();
        
        // create a copy of the patient and updated using the model API
        Patient.Builder patientBuilder = patient.toBuilder();
        List<HumanName> name = new ArrayList<>(patient.getName());
        patientBuilder.name(Collections.singletonList(
            name.get(0).toBuilder()
                .given(string("Jack"))
                .build()));
        Patient updatedPatient = patientBuilder.build();
        
        // create a Patch and apply it to the original patient
        JsonArray array = Json.createPatchBuilder()
                .add("/name/0/given/1", "Jack")
                .build().toJsonArray();
        FHIRJsonPatch patch = new FHIRJsonPatch(array);
        patient = patch.apply(toJsonObject(patient));
        
        Assert.assertEquals(patient, updatedPatient);
    }
    
    @Test
    public void testRemoveOperation() throws FHIRPatchException, FHIRGeneratorException {
        Patient patient = buildPatient();
        
        // create a copy of the patient and update it using the model API
        Patient.Builder patientBuilder = patient.toBuilder();
        patientBuilder.active(null);
        Patient updatedPatient = patientBuilder.build();
        
        // create a Patch and apply it to the original patient
        JsonArray array = Json.createPatchBuilder()
                .remove("/active")
                .build().toJsonArray();
        FHIRJsonPatch patch = new FHIRJsonPatch(array);
        patient = patch.apply(toJsonObject(patient));
        
        Assert.assertEquals(patient, updatedPatient);
    }
    
    @Test
    public void testReplaceOperation() throws FHIRPatchException, FHIRGeneratorException {
        Patient patient = buildPatient();
        
        // create a copy of the patient and update it using the model API
        Patient.Builder patientBuilder = patient.toBuilder();
        patientBuilder.active(Boolean.FALSE);
        Patient updatedPatient = patientBuilder.build();
        
        // create a Patch and apply it to the original patient
        JsonArray array = Json.createPatchBuilder()
                .replace("/active", false)
                .build().toJsonArray();
        FHIRJsonPatch patch = new FHIRJsonPatch(array);
        patient = patch.apply(toJsonObject(patient));
        
        Assert.assertEquals(patient, updatedPatient);
    }
    
    @Test
    public void testCopyOperation() throws FHIRPatchException, FHIRGeneratorException {
        Patient patient = buildPatient();
        
        // create a copy of the patient and update it using the model API
        Patient.Builder patientBuilder = patient.toBuilder();
        List<HumanName> name = new ArrayList<>(patient.getName());
        patientBuilder.name(Collections.singletonList(
            name.get(0).toBuilder()
                .family(patient.getName().get(0).getGiven().get(0))
                .build()));
        Patient updatedPatient = patientBuilder.build();
        
        // create a Patch and apply it to the original patient
        JsonArray array = Json.createPatchBuilder()
                .copy("/name/0/family", "/name/0/given/0")
                .build().toJsonArray();
        FHIRJsonPatch patch = new FHIRJsonPatch(array);
        patient = patch.apply(toJsonObject(patient));
        
        Assert.assertEquals(patient, updatedPatient);
    }
    
    @Test
    public void testMoveOperation() throws FHIRGeneratorException, FHIRPatchException {
        Patient patient = buildPatient();
        
        // create a copy of the patient and update it using the model API
        Patient.Builder patientBuilder = patient.toBuilder();
        patientBuilder.meta(patient.getMeta().toBuilder().versionId(null).build());
        List<HumanName> name = new ArrayList<>(patient.getName());
        patientBuilder.name(Collections.singletonList(
            name.get(0).toBuilder()
                .family(string(patient.getMeta().getVersionId().getValue()))
                .build()));
        Patient updatedPatient = patientBuilder.build();
        
        // create a Patch and apply it to the original patient
        JsonArray array = Json.createPatchBuilder()
                .move("/name/0/family", "/meta/versionId")
                .build().toJsonArray();
        FHIRJsonPatch patch = new FHIRJsonPatch(array);
        patient = patch.apply(toJsonObject(patient));
        
        Assert.assertEquals(patient, updatedPatient);
    }
    
    public void print(Resource resource) throws FHIRGeneratorException {
        FHIRGenerator.generator(Format.JSON, true).generate(resource, nonClosingOutputStream(System.out));
    }
    
    public <T extends Resource> T toResource(JsonObject jsonObject) throws FHIRParserException {
        return FHIRParser.parser(Format.JSON).as(FHIRJsonParser.class).parse(jsonObject);
    }
    
    public JsonObject toJsonObject(Resource resource) throws FHIRGeneratorException {
        StringWriter writer = new StringWriter();
        FHIRGenerator.generator(Format.JSON).generate(resource, writer);
        return Json.createReader(new StringReader(writer.toString())).readObject();
    }
    
    public OutputStream nonClosingOutputStream(OutputStream out) {
        return new FilterOutputStream(out) {
            @Override
            public void close() {
                // do nothing
            }
        };
    }

    private Patient buildPatient() {
        java.lang.String div = "<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative</b></p></div>";
        
        Id id = Id.builder()
                .value(UUID.randomUUID().toString())
                .build();
        
        Meta meta = Meta.builder()
                .versionId(Id.of("1"))
                .lastUpdated(Instant.now(ZoneOffset.UTC))
                .build();
        
        HumanName name = HumanName.builder()
                .given(string("John"))
                .family(string("Doe"))
                .build();
        
        Narrative text = Narrative.builder()
                .status(NarrativeStatus.GENERATED)
                .div(div)
                .build();
        
        return Patient.builder()
                .id(id)
                .meta(meta)
                .text(text)
                .active(Boolean.TRUE)
                .name(name)
                .birthDate(Date.of("1980-01-01"))
                .build();
    }
}
