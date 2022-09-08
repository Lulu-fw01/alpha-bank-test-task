import 'package:client/client/gif_api_client.dart';
import 'package:client/exceptions/get_gif_exception.dart';
import 'package:client/models/gif_dto.dart';


abstract class GifRepository {
  Future<GifDto> getGif({required String code});
}

class HttpGifRepository implements GifRepository {
  HttpGifRepository({required this.gifClient});
  
  final GifApi gifClient;

  @override
  Future<GifDto> getGif({required String code}) async {
    try {
      final gif = await gifClient.getGif(code);
      return gif;
    } on Exception catch (e) {
      throw GetGifException(e.toString());
    }
  }
}