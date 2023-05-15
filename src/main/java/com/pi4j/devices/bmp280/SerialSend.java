package com.pi4j.devices.bmp280;

import com.fazecast.jSerialComm.SerialPort;
import java.nio.charset.StandardCharsets;

public class SerialSend {
    public static void sendData(String text){
        int BaudRate = 9600;
        int DataBits = 8;
        int StopBits = SerialPort.ONE_STOP_BIT;
        int Parity   = SerialPort.NO_PARITY;


        SerialPort MySerialPort = SerialPort.getCommPort("COM4");
        MySerialPort.setComPortParameters(BaudRate,DataBits,StopBits,Parity);//Sets all serial port parameters at one time
        MySerialPort.openPort(); //open the port

        // add a delay of 1 second after opening to initialize and stabilize the serial port connection
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (MySerialPort.isOpen()) {
            System.out.println("\n" + MySerialPort.getSystemPortName() + "  is open ");
        }
        else {
            System.out.println(" Port not open ");
        }

        try {
            try {
                //String text = "Hallo";
                byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
                MySerialPort.getOutputStream().write(bytes);
                MySerialPort.getOutputStream().flush();
                MySerialPort.getOutputStream().close();
                System.out.println(" Text Transmitted -> " + text );

                //Or like this

                //int bytesTxed  = 0;
                //bytesTxed  = MySerialPort.writeBytes(bytes,bytes.length);

                //System.out.print(" Bytes Transmitted -> " + bytesTxed );

            }
            catch (Exception e) {
                e.printStackTrace();
            }
            //Add a delay of 1 second to send all data
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MySerialPort.closePort();
        if (MySerialPort.isOpen()){
            System.out.println(MySerialPort.getSystemPortName() + " is still open ");
        }
        else {
            System.out.println("\n Port closed ");
        }
    }

}
