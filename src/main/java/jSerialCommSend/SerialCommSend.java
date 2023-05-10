package jSerialCommSend;

import com.fazecast.jSerialComm.SerialPort;

import java.nio.charset.StandardCharsets;

public class SerialCommSend {
    public static void main(String[] args) {
        //Print all available serial ports
        SerialPort[] AvailablePorts = SerialPort.getCommPorts();
        System.out.print("\n\n Available Ports ");
        for (int i = 0; i<AvailablePorts.length ; i++) {
            System.out.println(i + " - " + AvailablePorts[i].getSystemPortName() + " -> " + AvailablePorts[i].getDescriptivePortName());
        }

        SerialPort comPort = SerialPort.getCommPorts()[1];
        comPort.openPort();
        System.out.println("\n" + comPort.getSystemPortName());
        try {
            while (true)
            {
                while (comPort.bytesAvailable() == 0)
                    Thread.sleep(20);

                byte[] readBuffer = new byte[comPort.bytesAvailable()];
                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                System.out.println("Read " + numRead + " bytes.");
                String text = new String(readBuffer, StandardCharsets.UTF_8);
                System.out.println("Received -> "+ text);
            }
        } catch (Exception e) { e.printStackTrace(); }
        comPort.closePort();
    }
}


