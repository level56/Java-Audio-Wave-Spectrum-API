plugins {
    id("application")    
    id("org.openjfx.javafxplugin") version "0.0.13"
}

javafx {
    version = "19"
    modules("javafx.controls", "javafx.fxml", "javafx.graphics", "javafx.web")
}

repositories {
    mavenCentral()
    maven( url = "https://mvnrepository.com/artifact/commons-io/commons-io")
    maven(url = "https://mvnrepository.com/artifact/ws.schild/jave-all-deps")
    maven(url = "https://mvnrepository.com/artifact/org.apache.pdfbox/pdfbox")
    maven(url = "https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api")
}

dependencies {	
    implementation("ws.schild:jave-all-deps:3.3.1")
    implementation("commons-io:commons-io:2.13.0")
    implementation("org.apache.pdfbox:pdfbox:3.0.0-alpha3")
    implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")

}

application {
    getMainClass().set("io.level56.waveapi.application.Main")
}

tasks {
	jar {
		manifest {
			attributes( "Main-Class" to "io.level56.waveapi.application.Main" ) 
		}
    fun includeJars() {fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar")))}
	exclude( "module-info.class" )
		exclude( "META-INF/versions/*/module-info.class" )
    }
}
