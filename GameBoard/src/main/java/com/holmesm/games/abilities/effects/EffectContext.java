package com.holmesm.games.abilities.effects;

import java.util.List;

import com.holmesm.games.board.path.Path;
import com.holmesm.games.pieces.GamePiece;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EffectContext {

	private GamePiece user;
	private List<GamePiece> targets;
	private Path path;
}
