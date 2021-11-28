import 'package:fluttauth/appNot/auth/test.dart';
import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';

// import './loginPage.dart';
// import './stateFul.dart';
// import './coneWhatsapp.dart';
// import './landingPage.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Test(),
    );
  }
}
