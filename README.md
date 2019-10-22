# Online Chess Game

### Compiling the source code:

Clone the repository into your computer.
Then open a Terminal/Command Prompt in the Download directory and run the following commands:
```
cd chess-server/src
mkdir out && javac com/chess/Main.java -d out
mv out/ ../ && cd .. && cp -r res/* out/
```

### Making an executable JAR File:

After compiling the code, execute the following command in the **out** directory:
```
jar cvf chess.jar .
```

### Running the JAR File:

#### To launch the server:

Run the following command in the terminal in the directory of JAR File.
```
java -jar chess.jar server 4444
```

Replace `4444` by a suitable network port number.

#### To launch the Client:

Run the following command in the terminal in the directory of JAR File.

To create a new Game:
```
java -jar chess.jar client localhost 4444
```

To join an existing Game:
```
java -jar chess.jar client localhost 4444 0
```

Note: 
- Replace `localhost` by Server's IP Address.
- Replace `4444` by the port number given by server.
- Replace `0` by the gameID displayed on first player's User Interface.

Also, make sure that the server and both clients are on the same network.

#### Client not connecting to the Server?

If somehow the Client is unable to connect to the Server, make sure the Server's firewall has allowed other devices to connect through that port.
To do so, follow these instructions:

##### For Debian based systems:
Check the firewall status using:
```
systemctl status ufw
```
If firewall is active, then run the following command:
```
sudo ufw allow 4444
```
Replace `4444` by the required port number.

##### For Windows:
- Navigate to Control Panel -> System and Security -> Windows Firewall.
- Select Advanced settings and highlight Inbound Rules in the left pane.
- Right click Inbound Rules and select New Rule.
- Add the port you need to open and click Next.
- Add the protocol `TCP` and the port number into the next window and click Next.
- Select Allow the connection in the next window and hit Next.
- Select the network type as you see fit and click Next.
- Name the rule something meaningful and click Finish.

##### For other Linux based systems:
  Refer this [link](https://www.ibm.com/support/knowledgecenter/en/STXKQY_5.0.0/com.ibm.spectrum.scale.v5r00.doc/bl1adv_firewallportopenexamples.htm).
  
##### For MacOSX:
  Refer this [link](https://gauravsohoni.wordpress.com/2015/04/14/mac-osx-open-port/).

### Happy Chess!
