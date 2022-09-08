part of 'home_bloc.dart';

@immutable
abstract class HomeEvent {}

class GetGifButtonClickedEvent implements HomeEvent {
  GetGifButtonClickedEvent({required this.code});
  final String code;
}
