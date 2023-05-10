module com.pi4j.devices{

    // Pi4J MODULES
    requires com.pi4j;
    requires com.pi4j.plugin.pigpio;

    // SLF4J MODULES   LOG4J
    requires org.slf4j;
    requires org.slf4j.simple;

   // requires org.apache.logging.log4j;
   // requires org.apache.logging.log4j.core;


    requires java.logging;
    requires jdk.unsupported;
    requires com.pi4j.plugin.linuxfs;
    requires java.desktop;
    requires com.fazecast.jSerialComm;


    uses com.pi4j.extension.Extension;
    uses com.pi4j.provider.Provider;
    exports com.pi4j.devices.bmp280 ;
    exports jSerialCommWrite;


}