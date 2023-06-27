

plugins {
    id("application")    
      //  id("org.jetbrains.kotlin.jvm") 
    id("org.openjfx.javafxplugin") version "0.0.13"
}

//subprojects {apply(plugin = "org.jetbrains.kotlin.jvm")}


javafx {
    version = "19"
    modules("javafx.controls", "javafx.fxml", "javafx.graphics", "javafx.web")
}

repositories {
    mavenCentral()
        maven( url = "https://mvnrepository.com/artifact/commons-io/commons-io")
        maven(url = "https://mvnrepository.com/artifact/ws.schild/jave-all-deps")

}

dependencies {	
// https://mvnrepository.com/artifact/ws.schild/jave-core
//implementation("ws.schild:jave-core:3.3.1")
implementation("ws.schild:jave-all-deps:3.3.1")
// https://mvnrepository.com/artifact/commons-io/commons-io
implementation("commons-io:commons-io:2.13.0")



}

application {
    getMainClass().set("io.level56.waveapi.application.Main")
}


// tasks {
// 	jar {
// 		manifest {
// 			attributes( "Main-Class" to "application.Main" ) 
// 		}
// fun includeJars() {fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar")))}
    

// 	exclude( "module-info.class" )
// 		exclude( "META-INF/versions/*/module-info.class" )
//     }}
