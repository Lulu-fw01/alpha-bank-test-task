import 'dart:convert';

import 'package:client/models/gif_dto.dart';
import 'package:http/http.dart' as http;

/// Api class for calling dollar gif service.
class GifApi {
  GifApi({required this.baseUrl});
  final String baseUrl;

  Future<GifDto> getGif(String code) async {
    final response = await http.get(Uri.parse(baseUrl + '/gif/$code'));

    if (response.statusCode == 200) {
      return GifDto.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Request error.');
    }
  }
}