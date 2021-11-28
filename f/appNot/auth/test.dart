import 'package:awesome_dialog/awesome_dialog.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
// import 'package:google_sign_in/google_sign_in.dart';

class Test extends StatefulWidget {
  @override
  _TestState createState() => _TestState();
}

class _TestState extends State<Test> {
  var myusrname, mypassword, myemail;
  GlobalKey<FormState> formState = new GlobalKey<FormState>();
  singup() async {
    var formdata = formState.currentState;
    if (formdata.validate()) {
      formdata.save();
      try {
        UserCredential userCredential = await FirebaseAuth.instance
            .createUserWithEmailAndPassword(
                email: myemail, password: mypassword);
        return userCredential;
      } on FirebaseAuthException catch (e) {
        if (e.code == 'weak-password') {
          AwesomeDialog(
              context: context,
              title: "Error",
              body: Text("The password provided is too weak."))
            ..show();
        } else if (e.code == 'email-already-in-use') {
          AwesomeDialog(
              context: context,
              title: "Error",
              body: Text("The account already exists for that email."))
            ..show();
        }
      } catch (e) {
        print(e);
      }
    } else {}
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // appBar: AppBar(title: Text("Signup")),
      body: Container(
        padding: EdgeInsets.all(30),
        child: ListView(
          children: [
            Center(
              child: Form(
                key: formState,
                child: Column(
                  children: [
                    Container(
                      margin: EdgeInsets.all(20),
                      child: Center(
                          child: Image.asset(
                        "images/img6.png",
                        width: 130,
                        height: 130,
                      )),
                    ),
                    TextFormField(
                        onSaved: (val) {
                          myusrname = val;
                        },
                        validator: (val) {
                          if (val.length > 20) {
                            return "Username Can't Be larger than 20 letter";
                          }
                          if (val.length < 4) {
                            return "Username Can't Be Less than 3 letter";
                          }
                          return null;
                        },
                        decoration: InputDecoration(
                            hintText: "Enter Ussername",
                            prefixIcon: Icon(Icons.person),
                            border: OutlineInputBorder(
                                borderSide: BorderSide(width: 2)))),
                    SizedBox(height: 10),
                    TextFormField(
                        onSaved: (val) {
                          myemail = val;
                        },
                        validator: (val) {
                          if (val.length > 20) {
                            return "email Can't Be larger than 20 letter";
                          }
                          if (val.length < 4) {
                            return "email Can't Be Less than 3 letter";
                          }
                          return null;
                        },
                        decoration: InputDecoration(
                            hintText: "Enter Email",
                            prefixIcon: Icon(Icons.email),
                            border: OutlineInputBorder(
                                borderSide: BorderSide(width: 2)))),
                    SizedBox(height: 10),
                    TextFormField(
                        onSaved: (val) {
                          mypassword = val;
                        },
                        validator: (val) {
                          if (val.length > 20) {
                            return "Password Can't Be larger than 20 letter";
                          }
                          if (val.length < 4) {
                            return "Password Can't Be Less than 3 letter";
                          }
                          return null;
                        },
                        obscureText: true,
                        decoration: InputDecoration(
                            hintText: "Enter Password",
                            prefixIcon: Icon(Icons.security),
                            border: OutlineInputBorder(
                                borderSide: BorderSide(width: 2)))),
                    Container(
                        padding: EdgeInsets.only(top: 10),
                        child: Row(
                          children: [
                            Text("if You Have accont "),
                            InkWell(
                              onTap: () {
                                Navigator.of(context).pushNamed("login");
                              },
                              child: Text(
                                "Click Here ?",
                                style: TextStyle(color: Colors.blue),
                              ),
                            )
                          ],
                        )),
                    Container(
                      padding: EdgeInsets.all(30),
                      child: MaterialButton(
                        splashColor: Colors.green,
                        padding:
                            EdgeInsets.symmetric(vertical: 15, horizontal: 45),
                        elevation: 10,
                        onPressed: () async {
                          UserCredential response = await singup();
                          if (response != null) {
                            Navigator.of(context)
                                .pushReplacementNamed("HomePage");
                          }
                        },
                        child: Text(
                          "Sign up",
                          style: TextStyle(color: Colors.white, fontSize: 17),
                        ),
                        color: Colors.blue,
                      ),
                    )
                  ],
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
