import 'package:client/client/gif_api_client.dart';
import 'package:client/repository/gif_repository.dart';
import 'package:client/ui/screens/bloc/home_bloc.dart';
import 'package:client/ui/screens/home_screen.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: BlocProvider(
        create: ((context) => HomeBloc(
            gifRepository: HttpGifRepository(
                gifClient:
                    GifApi(baseUrl: 'http://localhost:5000/currency-gif')))),
        child: const HomeScreen(),
      ),
    );
  }
}
