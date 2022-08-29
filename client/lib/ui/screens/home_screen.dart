import 'package:client/ui/screens/bloc/home_bloc.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

/// Home screen of web app.
class HomeScreen extends StatelessWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final currencyBaseController = TextEditingController();
    String currencyCode = '';
    currencyBaseController.addListener(() {
      currencyCode = currencyBaseController.text;
    });
    return Scaffold(
      body: Container(
        padding: EdgeInsets.all(128),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            TextField(
              controller: currencyBaseController,
              textAlign: TextAlign.center,
            ),
            const SizedBox(
              height: 8,
            ),
            ElevatedButton(onPressed: () {_onGetGifClicked(context, currencyCode);}, child: const Text('Get gif')),
            const SizedBox(
              height: 8,
            ),
            Image.network(
                'https://res.cloudinary.com/practicaldev/image/fetch/s--s2WZChX_--/c_limit%2Cf_auto%2Cfl_progressive%2Cq_66%2Cw_880/https://roszkowski.dev/images/2020-05-04/flutter_logo_leg.gif')
          ],
        ),
      ),
    );
  }

  static void _onGetGifClicked(BuildContext context, String code) {
    BlocProvider.of<HomeBloc>(context).add(GetGifButtonClickedEvent(code: code));
  }
}
