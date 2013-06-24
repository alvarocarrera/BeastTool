package es.upm.dit.gsi.beast.reader.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import es.upm.dit.gsi.beast.exception.BeastException;
import es.upm.dit.gsi.beast.reader.Reader;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.reader.system.SystemReaderTest.java
 * 
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @version 0.1
 * 
 */
public class SystemReaderTest {

    @Test
    public void MainSystemReaderTest() {
        this.cleanUp();
        try {
            SystemReader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/SystemReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties");
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "CaseManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "SystemStory.java").exists());
            this.cleanUp();
    }

    @Test
    public void MainReaderWithoutLogPropTest() {
        this.cleanUp();
        try {
            SystemReader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/SystemReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "es.upm.dit.gsi.beast.reader.system.test", null);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "CaseManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "SystemStory.java").exists());

        this.cleanUp();
    }
    
    @Test
    public void MainReaderTest() {
        this.cleanUp();
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/SystemReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    Reader.SYSTEM);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "CaseManager.java").exists());
        Assert.assertTrue(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test",
                "SystemStory.java").exists());
        this.cleanUp();
    }
    
    @Test
    public void CaseManagerDuplicatedMethodsTest() {
        this.cleanUp();
        boolean catched = false;
        String message="";
        try {
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/SystemReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    Reader.SYSTEM);
            Reader.generateJavaFiles(
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/SystemReaderTest.story",
                    "\"jade\"", "src/test/java",
                    "es.upm.dit.gsi.beast.reader.system.test.algo",
                    "es.upm.dit.gsi.beast.reader.system.test",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    Reader.SYSTEM);
        } catch (Exception e) {
            message = e.getMessage();
            catched = true;
        }
        Assert.assertTrue(catched);
        Assert.assertTrue(message.startsWith("Two different stories with the same name (same method name) are being created in the same CaseManager file."));
        this.cleanUp();
        
    }

    @Test
    public void CaseManagerNotDeletedSystemTest() {
        this.cleanUp();
        boolean passed = false;
        try {
            Reader.generateJavaFiles(
                    "src/test/resources/",
                    "\"jade\"",
                    "src/test/java",
                    "es.upm.dit.gsi.beast.reader.test",
                    "es.upm.dit.gsi.beast.reader.test.manager",
                    "src/test/java/es/upm/dit/gsi/beast/reader/system/log.properties",
                    "SYSTEM");
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            File folder = SystemReader
                    .createFolder("es.upm.dit.gsi.beast.reader.test.manager",
                            "src/test/java");
            File caseManager = new File(folder, "CaseManager.java");

            String targetLine1 = "     JUnitCore.runClasses(es.upm.dit.gsi.beast.reader.test.ExampleStories.A1.class);";
            String targetLine2 = "     JUnitCore.runClasses(es.upm.dit.gsi.beast.reader.test.ExampleStories.A2.class);";

            BufferedReader r = new BufferedReader(new FileReader(caseManager));
            String in;
            while ((in = r.readLine()) != null) {
                if (targetLine1.equals(in)) {
                    while ((in = r.readLine()) != null) {
                        if (targetLine2.equals(in)) {
                            passed = true;
                            break;
                        }
                    }
                }
                if (passed) {
                    break;   
                }
            }
            r.close();
        } catch (BeastException e) {
            Assert.fail();
        } catch (FileNotFoundException e) {
            Assert.fail();
        } catch (IOException e) {
            Assert.fail();
        }
        Assert.assertTrue(passed);
        this.cleanUp();
    }

    private void cleanUp() {
        this.deleteDirectory(new File(
                "src/test/java/es/upm/dit/gsi/beast/reader/system/test"));
    }

    private boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }
}
