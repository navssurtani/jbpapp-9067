/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2009 Red Hat Middleware, Inc. and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.test.scripts.test;

import java.io.File;
import java.net.URL;
import junit.framework.Test;

/**
 * Unit tests of wsrunclient.sh and wsrunclient.bat.
 *
 * @author Rostislav Svoboda
 * @version $Revision: $
 */
public class WsclientTestCase extends ScriptsTestBase {
    private static final URL classesDirUrl = Thread.currentThread().getContextClassLoader().getResource("");

    /**
     * Create a new WsclientTestCase.
     *
     * @param name
     */
    public WsclientTestCase(String name) {
        super(name);
    }

    // WAR file from org.jboss.test.webservice.endpoint
    public static Test suite() throws Exception {
        return getDeploySetup(WsclientTestCase.class, "wsrunclient-test.war");
    }

    /**
     * Prints out some basic info about the environment
     */
    public void testExecutionEnvironment() {
        String os = isWindows() ? "Windows" : "non-Windows";
        // dump out some basic config information
        System.out.println("Testing run on " + os + " host");
        System.out.println("Working directory: " + getBinDir());
        System.out.println("Dist directory: " + getDistDir());
        System.out.println("Log directory: " + getLogDir());
        System.out.println("Server config: " + getServerConfig());
        System.out.println("CXF installed: " + isCXFInstalled());
    }

    /**
     * Tests if IPv4 is forced to be used
     */
    public void testIPv4() {
        String suffix = isWindows() ? ".bat" : ".sh";
        String ipv4Text = "-Djava.net.preferIPv4Stack=true";
        File file = new File(getBinDir() + FS + "wsrunclient" + suffix);

        if (!fileContainsText(file, ipv4Text)) {
            fail("File " + file.getAbsolutePath()  + " doesn't contain '" + ipv4Text + "' text");
        }
    }

    /**
     * Tests run "help" command (no args)
     *
     * @throws Exception
     */
    public void testNoArgs() throws Exception {
        String command = "wsrunclient";
        String options = null;
        String args = null;
        String[] shellCommand = getShellCommand(command, options, args);

        String[] envp = null;                       // set the environment if necessary
        File workingDir = new File(getBinDir());    // set the working directory
        getShellScriptExecutor().runShellCommand(shellCommand, envp, workingDir);

        // check assertions
        getShellScriptExecutor().assertOnOutputStream("usage: wsrunclient", "usage string not found in command output");
    }

    /**
     * Tests to run client
     *
     * @throws Exception
     */
    public void testRunClient() throws Exception {
        String command = "wsrunclient";
        // original package was org.jboss.test.ws.benchmark.jaxws.doclit
        String options = "-classpath " + classesDirUrl.getFile();
        String args = "org.jboss.test.scripts.support.EchoClient";
        String[] shellCommand = getShellCommand(command, options, args);

        String[] envp = null;                       // set the environment if necessary
        File workingDir = new File(getBinDir());    // set the working directory
        getShellScriptExecutor().runShellCommand(shellCommand, envp, workingDir);

        // check assertions
        getShellScriptExecutor().assertOnOutputStream("Hello world!", "'Hello world!' string not found in command output");
    }
    /**
     * Tests to run non exixting client
     *
     * @throws Exception
     */
    public void testRunNonExistingClient() throws Exception {
        String command = "wsrunclient";
        // original package was org.jboss.test.ws.benchmark.jaxws.doclit
        String options = "-classpath " + classesDirUrl.getFile();
        String args = "org.jboss.test.scripts.support.NonExistingClient";
        String[] shellCommand = getShellCommand(command, options, args);

        String[] envp = null;                       // set the environment if necessary
        File workingDir = new File(getBinDir());    // set the working directory
        getShellScriptExecutor().runShellCommand(shellCommand, envp, workingDir);

        getShellScriptExecutor().assertOnErrorStream("java.lang.ClassNotFoundException",
                "'java.lang.ClassNotFoundException' string not found in error output");
    }
}
