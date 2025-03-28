import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LanguageProvider extends ChangeNotifier {
  String _selectedLanguage = "en";
  String get selectedLanguage => _selectedLanguage;

  LanguageProvider() {
    loadLanguage();
  }

  void changeLanguage(String lang) {
    _selectedLanguage = lang;
    saveLanguage();
    notifyListeners();
  }

  Future<void> loadLanguage() async {
    final prefs = await SharedPreferences.getInstance();
    _selectedLanguage = prefs.getString('language') ?? "en";
    notifyListeners();
  }

  Future<void> saveLanguage() async {
    final prefs = await SharedPreferences.getInstance();
    prefs.setString('language', _selectedLanguage);
  }
}
