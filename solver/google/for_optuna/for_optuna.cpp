#include "../for_optuna/for_optuna.h"

#include <iostream>

void FileData::Input() {
	cin >> max_turn;
	cin >> width;
	cin >> height;
	for (int_fast32_t &&h = 0; h < height; ++h) {
		for (int_fast32_t &&w = 0; w < width; ++w) {
			cin >> field_data[h][w];
		}
	}
	cin >> agent_num;
	for (int_fast32_t &&team_id = 0; team_id < 2; ++team_id) {
		for (int_fast32_t &&agent_id = 0; agent_id < agent_num; ++agent_id) {
			cin >> agents_position[team_id][agent_id].w
				>> agents_position[team_id][agent_id].h;
		}
	}

	return;
}

void FileData::InputGameData(GameData &game_data) {
	game_data.max_turn = max_turn;
	game_data.width = width;
	game_data.height = height;
	game_data.agent_num = agent_num;

	for (int_fast32_t &&h = 0; h < height; ++h) {
		for (int_fast32_t &&w = 0; w < width; ++w) {
			cin >> game_data.field_data[h][w];
		}
	}

	return;
}

void FileData::InputTurnData(const GameData &game_data, TurnData &turn_data) {
	turn_data.reset();

	for (auto &column : turn_data.tile_data)
		fill(column.begin(), column.end(), kBrank);

	for (int_fast32_t &&team_id = 0; team_id < 2; ++team_id) {
		for (int_fast32_t &&agent_id = 0; agent_id < game_data.agent_num;
			 ++agent_num) {
			const Position &position =
					agents_position[team_id][agent_id];

			turn_data.tile_data[position.h][position.w] = team_id;
			turn_data.agents_position[team_id][agent_id] = position;
			turn_data.agent_exist.set(GetBitsetNumber(position));
		}
	}
	turn_data.agent_num = game_data.agent_num;
	turn_data.CalculationAllTilePoint(game_data);
	turn_data.CalculationAllAreaPoint(game_data);

	return;
}
