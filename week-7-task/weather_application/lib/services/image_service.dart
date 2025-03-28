import 'dart:convert';
import 'package:http/http.dart' as http;
import '../utils/constants.dart';

class ImageService {
  Future<String> fetchWeatherImage(String query) async {
    final url = Uri.parse('https://api.pexels.com/v1/search?query=$query');
    
    final response = await http.get(url, headers: {"Authorization": pexelsApiKey});
    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      return data['photos'][0]['src']['medium'];
    } else {
      throw Exception("Failed to load weather image");
    }
  }
}
