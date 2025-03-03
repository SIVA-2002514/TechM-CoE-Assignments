import { useEffect, useState } from "react";
import axios from "axios";
import Sidebar from "./components/Sidebar";
import GameCard from "./components/GameCard";
import "./components/sidebar.css";
import "./components/gameList.css";

const API_KEY = "2bd8edd88ba442afad9adf3f52273c57"; 
const API_URL = `https://api.rawg.io/api/games?key=${API_KEY}`;

const App = () => {
  interface Game {
    id: number;
    name: string;
    background_image: string;
    rating: number;
    released: string;
    genres: { name: string }[];
    platforms: { platform: { name: string } }[];
  }

  const [games, setGames] = useState<Game[]>([]);
  const [page, setPage] = useState(1);
  const [searchQuery, setSearchQuery] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    setLoading(true);
    setError(null);
    axios
      .get(`${API_URL}&page=${page}`)
      .then((res) => {
        setGames(res.data.results);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setError("Failed to fetch games");
        setLoading(false);
      });
  }, [page]);

  return (
    <div className="app-container">
      <Sidebar />
      <div className="content">
        <h1 style={{ textAlign: "center", fontFamily: "Verdana, sans-serif", color: "#FF6347", margin: "20px 0" }}>Welcome To My Game Store</h1>
        <div style={{ textAlign: "center", margin: "20px 0" }}>
          <input 
            type="text" 
            placeholder="Search games..." 
            style={{ padding: "10px", width: "80%", maxWidth: "600px", borderRadius: "15px", border: "1px solid #ccc", fontFamily: "Arial, sans-serif", boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)" }} 
            onChange={(e) => {
              setSearchQuery(e.target.value.toLowerCase());
              if (e.target.value === "") {
          setPage(1); 
              }
            }}
          />
        </div>
        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}
        <div className="game-list-container">
          <div className="game-list">
        {games
          .filter((game) => game.name.toLowerCase().includes(searchQuery))
          .map((game) => (
            <GameCard key={game.id} game={game} />
          ))}
          </div>
        </div>
        <div className="pagination">
          <button 
            onClick={() => setPage((prev) => Math.max(prev - 1, 1))} 
            style={{ backgroundColor: "#4CAF50", color: "white", fontFamily: "Arial, sans-serif", padding: "10px 20px", border: "none", borderRadius: "5px", cursor: "pointer" }}
          >
            Prev
          </button>
          <span style={{ fontFamily: "Arial, sans-serif", padding: "10px 20px" }}>Page {page}</span>
          <button 
            onClick={() => setPage((prev) => prev + 1)} 
            style={{ backgroundColor: "#4CAF50", color: "white", fontFamily: "Arial, sans-serif", padding: "10px 20px", border: "none", borderRadius: "5px", cursor: "pointer" }}
          >
            Next
          </button>
        </div>
      </div>
    </div>
  );
}

export default App;
