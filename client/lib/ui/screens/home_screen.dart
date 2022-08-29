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
        padding: const EdgeInsets.all(128),
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
            _content()
          ],
        ),
      ),
    );
  }

  static void _onGetGifClicked(BuildContext context, String code) {
    BlocProvider.of<HomeBloc>(context).add(GetGifButtonClickedEvent(code: code));
  }

  /// Content of the screen. Depends on app state.
  Widget _content() => BlocBuilder<HomeBloc, HomeState>(builder: (context, state) {
    if (state is HomeInitial) {
      return Container();
    }
    if (state is HomeLoading) {
      return const CircularProgressIndicator();
    }
    if (state is HomeSuccess) {
      return Expanded(child: Image.network(state.gif.uri, fit: BoxFit.contain,),);
    }
    if (state is HomeError) {
      return const Text('error');
    } 
    return Container();
  }
  ,);
}
