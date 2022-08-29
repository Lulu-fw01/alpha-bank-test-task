import 'package:bloc/bloc.dart';
import 'package:client/models/gif_dto.dart';
import 'package:client/repository/gif_repository.dart';
import 'package:equatable/equatable.dart';
import 'package:meta/meta.dart';

part 'home_event.dart';
part 'home_state.dart';

/// Bloc for state management of home screen.
class HomeBloc extends Bloc<HomeEvent, HomeState> {
  HomeBloc({required this.gifRepository}) : super(HomeInitial()) {
    on<GetGifButtonClickedEvent>(_mapGetGifButtonClicked);
  }

  final GifRepository gifRepository;

  void _mapGetGifButtonClicked(
      GetGifButtonClickedEvent event, Emitter<HomeState> emit) async {
    emit(HomeLoading());
    try {
      final gif = await gifRepository.getGif(code: event.code);

      emit(HomeSuccess(gif: gif));
    } catch (error, stacktrace) {
      emit(HomeError(error: error.toString()));
    }
  }
}
