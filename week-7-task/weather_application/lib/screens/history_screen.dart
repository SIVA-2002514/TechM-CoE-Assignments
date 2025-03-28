import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

class HistoryScreen extends StatelessWidget {
  const HistoryScreen({super.key});

  void deleteHistoryItem(String docId) {
    FirebaseFirestore.instance.collection("search_history").doc(docId).delete();
  }

  void likeLocation(String city) async {
    final favoritesRef = FirebaseFirestore.instance.collection("favorites");
    final existing = await favoritesRef.where("city", isEqualTo: city).get();

    if (existing.docs.isEmpty) {
      await favoritesRef.add({"city": city, "timestamp": DateTime.now()});
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Search History")),
      body: StreamBuilder(
        stream: FirebaseFirestore.instance
            .collection("search_history")
            .orderBy("timestamp", descending: true)
            .snapshots(),
        builder: (context, AsyncSnapshot<QuerySnapshot> snapshot) {
          if (!snapshot.hasData) {
            return const Center(child: CircularProgressIndicator());
          }

          if (snapshot.data!.docs.isEmpty) {
            return const Center(child: Text("No search history available."));
          }

          return ListView(
            children: snapshot.data!.docs.map((doc) {
              String city = doc['city'];
              return Card(
                margin: const EdgeInsets.symmetric(vertical: 6, horizontal: 12),
                child: ListTile(
                  title: Text(city, style: const TextStyle(fontSize: 18)),
                  trailing: Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      IconButton(
                        icon: const Icon(Icons.favorite, color: Colors.red),
                        onPressed: () => likeLocation(city),
                      ),
                      IconButton(
                        icon: const Icon(Icons.delete, color: Colors.grey),
                        onPressed: () => deleteHistoryItem(doc.id),
                      ),
                    ],
                  ),
                ),
              );
            }).toList(),
          );
        },
      ),
    );
  }
}
