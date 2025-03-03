interface Game {
  background_image: string;
  name: string;
  rating: number;
  released: string;
  genres: { name: string }[];
    platforms: { platform: { name: string } }[];
  }

const GameCard = ({ game }: { game: Game }) => {
  return (
    <div className="game-card">
      <img src={game.background_image} alt={game.name} />
      <h3>{game.name}</h3>
      <p>⭐ {game.rating} |  {game.released}</p>
      <p><strong>Genres:</strong> {game.genres.map((g) => g.name).join(", ")}</p>
      <p><strong>Platforms:</strong> {game.platforms.map((p) => p.platform.name).join(", ")}</p>
      <button style={{ borderRadius: '5px', color: 'white', backgroundColor:'orange' , padding:'5px 10px' }}>Play Now</button>
      <button>Add to Wishlist</button>
    </div>
  );
};

export default GameCard;
