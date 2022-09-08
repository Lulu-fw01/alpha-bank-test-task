import 'package:flutter/material.dart';

/// Widget which shows gif image with animation.
class GifWidget extends StatefulWidget {
  const GifWidget({required this.uri, Key? key}) : super(key: key);

  final String uri;

  @override
  State<StatefulWidget> createState() => _GifWidgetState();
}

class _GifWidgetState extends State<GifWidget> with TickerProviderStateMixin {
  late AnimationController _controller;
  late Animation<double> _animation;

  @override
  void initState() {
    _controller =
        AnimationController(vsync: this, duration: const Duration(seconds: 2), value: 0.1);
    _animation = CurvedAnimation(curve: Curves.fastOutSlowIn, parent: _controller);
    _controller.forward();
    super.initState();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return ScaleTransition(
      scale: _animation,
      child: Image.network(
        widget.uri,
        fit: BoxFit.contain,
      ),
    );
  }
}
