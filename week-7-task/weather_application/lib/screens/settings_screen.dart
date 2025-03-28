import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/theme_provider.dart';
import '../providers/language_provider.dart';

class SettingsScreen extends StatelessWidget {
  const SettingsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final themeProvider = Provider.of<ThemeProvider>(context);
    final languageProvider = Provider.of<LanguageProvider>(context);

    return Scaffold(
      appBar: AppBar(title: const Text("Settings")),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // 🔲 Dark Mode Toggle
            SwitchListTile(
              title: const Text("Dark Mode", style: TextStyle(fontSize: 18)),
              value: themeProvider.isDarkMode,
              onChanged: (value) => themeProvider.toggleTheme(),
            ),
            const SizedBox(height: 20),

            // 🌍 Language Selection
            const Text("Select Language:", style: TextStyle(fontSize: 18)),
            DropdownButton<String>(
              value: languageProvider.selectedLanguage,
              items: const [
                DropdownMenuItem(value: 'en', child: Text("English")),
                DropdownMenuItem(value: 'es', child: Text("Spanish")),
                DropdownMenuItem(value: 'ta', child: Text("Tamil")),
              ],
              onChanged: (String? newLanguage) {
                if (newLanguage != null) {
                  languageProvider.changeLanguage(newLanguage);
                }
              },
            ),
          ],
        ),
      ),
    );
  }
}
