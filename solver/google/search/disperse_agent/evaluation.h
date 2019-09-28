#ifndef DISPERSE_AGENT_EVALUATION_H
#define DISPERSE_AGENT_EVALUATION_H

#include "../../calculation/calculation.h"

namespace disperse_agent {

const int_fast32_t evaluations_num = 7;

double GetEvaluation(const GameData&, TurnData&, const TurnData&,
					 const int_fast32_t&, const int_fast32_t&, const double&);

double AllyTilePointDifference(const GameData&, const TurnData&,
							   const TurnData&, const int_fast32_t&);

double RivalTilePointDifference(const GameData&, const TurnData&,
								const TurnData&, const int_fast32_t&);

double AllyAreaPointDifference(const GameData&, const TurnData&,
							   const TurnData&, const int_fast32_t&);

double RivalAreaPointDifference(const GameData&, const TurnData&,
								const TurnData&, const int_fast32_t&);

double DisperseAgent(const GameData&, const TurnData&, const int_fast32_t&);

double NotMyTeamMasu(const GameData&, const TurnData&,
					 const int_fast32_t&, const int_fast32_t&);

double BeforeEvaluationBias(const GameData&, const double&, const int_fast32_t&);

double FirstEvaluation(const GameData&, const double&, const int_fast32_t&);

}

#endif
