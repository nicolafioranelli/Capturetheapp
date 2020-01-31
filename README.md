# Capturetheapp

Capturetheapp is a spyware prototype for educational purposes. The objective of the app is to take advantage of Android libraries like MediaProjection to evaluate the app running in foreground and in case is one of those relevant, start a capture session.
The capture activity requires some privileges that are prompted to the user in a blear way. Moreover, each of the screenshots captured are then analyzed by an OCR (Google Vision MLKit) to send text to the C&C server.

## Future developments

Actually Capturetheapp exploits some social engineering techniques in order to be as much stealth as possible. For instance it is installed as Vodafone 5G app (Vodafone is a network provider in Italy) and it guides the user into several steps to obtain all the permissions.
Once it has permissions, it removes its icon from the launcher in order not be seen.

A possible alternative could be to try to use some exploits available for Android to perform a privilege escalation in order to avoid permission requests.

## Contributing

Please refer to the author for any issue or for possible enhancements.

## Authors

* **Nicola Fioranelli** - *Master Thesis in Computer Science*

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* 
