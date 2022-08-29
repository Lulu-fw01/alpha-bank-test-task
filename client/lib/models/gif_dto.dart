class GifDto {
  GifDto({required this.uri});
  factory GifDto.fromJson(Map<String, dynamic> json) {
    return GifDto(uri: json['url']);
  }
  String uri;
}
