import { useState } from "react";
import React from "react";
import { Drawer, IconButton, List, ListItem, ListItemText } from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
const Sidebar: React.FC = () => {
  const [open, setOpen] = useState(false);

  const handleCategoryClick = (category: string) => {
    console.log(`Selected category: ${category}`);
   
  };

  return (
    <div className="sidebar">
      <IconButton onClick={() => setOpen(true)} style={{ color: "white" }}>
        <MenuIcon />
      </IconButton>
      <Drawer anchor="left" open={open} onClose={() => setOpen(false)}>
        <div className="sidebar-content" style={{ backgroundColor: "#333", color: "white", height: "100%" }}>
          <h2 onClick={() => setOpen(!open)} style={{ color: "white" }}>Gaming Style</h2>
          <List>
        <ListItem component="button" onClick={() => handleCategoryClick("Action")} style={{ color: "#ff1744" }}>
          <ListItemText primary="Action" />
        </ListItem>
        <ListItem component="button" onClick={() => handleCategoryClick("RPG")} style={{ color: "#2979ff" }}>
          <ListItemText primary="RPG" />
        </ListItem>
        <ListItem component="button" onClick={() => handleCategoryClick("Strategy")} style={{ color: "#00e676" }}>
          <ListItemText primary="Strategy" />
        </ListItem>
        <ListItem component="button" onClick={() => handleCategoryClick("Adventure")} style={{ color: "#ff9100" }}>
          <ListItemText primary="Adventure" />
        </ListItem>
          </List>
        </div>
      </Drawer>
    </div>
  );
};

export default Sidebar;
