import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  String _currencyBase = "";
  final TextEditingController _currencyBaseController =
      new TextEditingController();

  @override
  void initState() {
    _currencyBaseController.addListener(() {
      _currencyBase = _currencyBaseController.text;
    });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        padding: EdgeInsets.all(128),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            TextField(
              controller: _currencyBaseController,
              textAlign: TextAlign.center,
            ),
            const SizedBox(
              height: 8,
            ),
            ElevatedButton(onPressed: () {}, child: Text('Get gif')),
            const SizedBox(height: 8,),
            Image.network('https://res.cloudinary.com/practicaldev/image/fetch/s--s2WZChX_--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_66%2Cw_880/https://roszkowski.dev/images/2020-05-04/flutter_logo_leg.gif')
          ],
        ),
      ),
    );
  }
}
