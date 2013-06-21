package es.upm.dit.gsi.beast.reader.mas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import es.upm.dit.gsi.beast.exception.BeastException;

/**
 * Project: beast
 * File: es.upm.dit.gsi.beast.reader.mas.CreateMASTestStory.java
 * 
 * Grupo de Sistemas Inteligentes
 * Departamento de Ingeniería de Sistemas Telemáticos
 * Universidad Politécnica de Madrid (UPM)
 * 
 * @author Alberto Mardomingo
 * @author Jorge Solitario
 * 
 * @author alvarocarrera
 * @email a.carrera@gsi.dit.upm.es
 * @twitter @alvarocarrera
 * @version 0.1
 * 
 */
public class CreateMASTestStory {

    /**
     * Method to create the java file that it's executed from caseManager. Its
     * name comes from the Scenario that it's testing. Its behaviour is written
     * in the .story file allocated in the same folder, which is the plain text
     * given by the client.
     * 
     * @param story_name - the name of the Story
     * @param platform_name - the name of the platform
     * @param package_path - the package path
     * @param dest_dir the main folder (typically src/main/java)
     * @param loggingPropFile
     * @param storyUser - The user launching the Story
     * @param userFeature - The feature requested by the user
     * @param userBenefit - The benefit the feature will provide
     * @param scenarios - A list with the tests to launch in the testSuite.
     * @throws BeastException 
     */
    public static void createMASTestStory(String story_name,
            String platform_name, String package_path, String dest_dir,
            String loggingPropFile, String storyUser,
            String userFeature, String userBenefit, HashMap<String, String[]> scenarios) throws BeastException {

        Logger logger = Logger.getLogger(CreateMASTestStory.class.getName());

        File f = MASReader.createFolder(package_path, dest_dir);

        String storyClass = MASReader.createClassName(story_name);
        File javaFile = new File(f, storyClass + ".java");

        try {
            FileWriter fw = new FileWriter(javaFile);
            fw.write("package " + package_path + ";\n");
            fw.write("\n");

            // Adds all the necessary imports.
            fw.write("import es.upm.dit.gsi.beast.story.logging.LogActivator;\n");
            fw.write("import es.upm.dit.gsi.beast.story.BeastTestCaseRunner;\n");
            fw.write("import java.io.FileInputStream;\n");
            fw.write("import java.io.IOException;\n");
            fw.write("import java.util.logging.Logger;\n");
            fw.write("import java.util.logging.Level;\n");
            fw.write("import java.util.logging.LogManager;\n");
            fw.write("import java.util.Properties;\n");
            fw.write("import org.junit.Test;\n");
            fw.write("\n");

            // Class header
            fw.write("/**\n");
            fw.write(" * Main class to translate plain text into code, following the Given-When-Then\n");
            fw.write(" * language. In the GIVEN part it launchs the platform In the WHEN part it\n");
            fw.write(" * configures the state of its agents. In the THEN part it checks the correct\n");
            fw.write(" * behaviour. The main purpose of it consists of knowing agents' state/properties\n");
            fw.write(" * without changing its code.\n");
            fw.write(" * \n");
            fw.write(" * @author es.upm.dit.gsi.beast\n");
            fw.write(" */\n");
            fw.write("public class " + storyClass  + "{\n");
            fw.write("\n");
            fw.write("    public Logger logger = Logger.getLogger("
                    + storyClass + ".class.getName());\n");
            fw.write("\n");

            // Creates the constructor
            fw.write("    /**\n");
            fw.write("     * Constructor to configure logging\n");
            fw.write("     */\n");
            fw.write("    public " + storyClass + "() {\n");
            if (loggingPropFile == null) {
                // If there is no properties, creates a "Standard" logger.
                fw.write("         LogActivator.logToFile(logger, "
                        + storyClass + ".class.getName(), Level.ALL);\n");
            } else {
                fw.write("         Properties preferences = new Properties();\n");
                fw.write("         try {\n");
                fw.write("              FileInputStream configFile = new FileInputStream(\""
                        + loggingPropFile + "\");\n");
                fw.write("              preferences.load(configFile);\n");
                fw.write("              LogManager.getLogManager().readConfiguration(configFile);\n");
                fw.write("              LogActivator.logToFile(logger, "
                        + storyClass + ".class.getName(), Level.ALL);\n");
                fw.write("         } catch (IOException ex) {\n");
                fw.write("              logger.severe(\"WARNING: Could not open configuration file\");\n");
                fw.write("         }\n");
            }
            fw.write("    }\n");
            fw.write("\n");
            
            // Run each test
            // Remenber; scenarios:
            // { scenarioID1 => ["Given", "When", "then"], 
            // scenarioID2 => ["Given", "When", "then"], ...}
            // BE AWARE: The scenarios are plain test WITH SPACES!!
            
            for(String scenario : scenarios.keySet()){
                fw.write("  /**\n");
                fw.write("   * This is the scenario: " + scenario
                        + ",\n");
                fw.write("   * where the GIVEN is described as: "
                        + scenarios.get(scenario)[0] + ",\n");
                fw.write("   * the WHEN is described as: " + scenarios.get(scenario)[1]
                        + "\n");
                fw.write("   * and the THEN is described as: "
                        + scenarios.get(scenario)[2] + "\n");
                fw.write("   */\n");
                fw.write("    @Test\n");
                fw.write("    public void " + MASReader.changeFirstLetterToLowerCase(
                        MASReader.createClassName(scenario)) + "(){\n");
                fw.write("        BeastTestCaseRunner.executeBeastTestCase(\"" + 
                		package_path + "." + MASReader.createFirstLowCaseName(story_name) +
                		"." + MASReader.createClassName(scenario) + "\");\n");
                fw.write("    }\n");
            }
            // This is how I can launch the jbehave test from a TestSuite,
            // since they are not junit TestCases.
            // This is not my fault, don't kill me, please.
           
            // Ends the class.
            // You don't say.
            fw.write("\n");
            fw.write("}\n");
            fw.write("\n");
            fw.flush();
            fw.close();

            // logger.info(scenario_name+" has been created in "+dest_dir+" "+Reader.createFolderPath(package_path));

        } catch (IOException e) {

            logger.severe("ERROR: The file " + story_name
                    + ".java can not be writed");
        }
    }   
}
