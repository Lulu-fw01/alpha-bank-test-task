class GetGifException implements Exception {
  GetGifException(this.message);
  final String message;

  @override
  String toString() => 'GetGifException(message: $message)';
}
