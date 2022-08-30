import 'package:client/ui/screens/bloc/home_bloc.dart';
import 'package:client/ui/widgets/gif_widget.dart';
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
            Expanded(
              flex: 1,
              child: Column(
                children: [
                  TextField(
                    maxLength: 3,
                    controller: currencyBaseController,
                    textAlign: TextAlign.center,
                  ),
                  const SizedBox(
                    height: 8,
                  ),
                  ElevatedButton(
                      onPressed: () {
                        _onGetGifClicked(context, currencyCode);
                      },
                      child: const Text('Get gif')),
                ],
              ),
            ),
            Expanded(flex: 1, child: _content())
          ],
        ),
      ),
    );
  }

  static void _onGetGifClicked(BuildContext context, String code) {
    BlocProvider.of<HomeBloc>(context)
        .add(GetGifButtonClickedEvent(code: code));
  }

  /// Content of the screen. Depends on app state.
  Widget _content() => BlocBuilder<HomeBloc, HomeState>(
        builder: (context, state) {
          if (state is HomeInitial) {
            return Container();
          }
          if (state is HomeLoading) {
            return const Center(child: CircularProgressIndicator());
          }
          if (state is HomeSuccess) {
            return GifWidget(uri: state.gif.uri);
          }
          if (state is HomeError) {
            return const Center(child: Text('error'));
          }
          return Container();
        },
      );
}
