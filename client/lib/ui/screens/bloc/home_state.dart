part of 'home_bloc.dart';

@immutable
abstract class HomeState {}

class HomeInitial extends HomeState {
}

class HomeLoading extends HomeState {
}

class HomeSuccess extends HomeState {
  HomeSuccess({required this.gif});
  final GifDto gif;
}

class HomeError extends HomeState {
  HomeError({required this.error});
  final String error;
}
