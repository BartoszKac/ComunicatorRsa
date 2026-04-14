ComunicatorRsa — A secure real-time chat application with end-to-end RSA encryption, built with JavaFX and Spring Boot.

🛠️ Tech Stack
Backend:

Java 21
Spring Boot 3.5
Spring WebSocket (STOMP)
Spring Data JPA
H2 Database (embedded)
Maven

Frontend (Desktop Client):

Java 21
JavaFX 21
Java-WebSocket
Maven


✨ Key Features

End-to-end RSA Encryption — Every message is encrypted on the sender's machine using the recipient's public RSA key before being sent over the network. The server never sees plaintext. Keys are generated locally and persisted on disk (user_keys.properties).
Real-time Messaging via WebSocket — Messages are delivered instantly using a STOMP WebSocket connection. Both sender and recipient stay connected to the /topic/messages broker channel.
Message History — Full conversation history is stored server-side in an H2 database and loaded on chat open. Sent messages are also stored locally as plaintext so the sender can re-read their own messages (which they cannot decrypt from the server copy).
Contact List & User Discovery — Users can search for other registered accounts by username. The backend returns the target user's public RSA key, which is used to encrypt subsequent messages. Contacts are saved locally for quick access.



<img width="473" height="708" alt="image" src="https://github.com/user-attachments/assets/10e125f9-78a6-4042-a9f6-7ce976bbb836" />


<img width="510" height="810" alt="image" src="https://github.com/user-attachments/assets/0c523d9f-8832-44da-b33a-5b330c3c4c91" />


<img width="955" height="506" alt="image" src="https://github.com/user-attachments/assets/d36f4db1-c013-4f5e-a423-b691dd86bde4" />

