<?php

namespace app\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\vehicleseatreservation;

/**
 * VehicleSeatReservationClass represents the model behind the search form about `app\models\vehicleseatreservation`.
 */
class VehicleSeatReservationClass extends vehicleseatreservation
{
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['vehicle_id', 'seat_reservation_id', 'user_id'], 'integer'],
            [['time_start', 'time_end', 'date', 'remarks'], 'safe'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function scenarios()
    {
        // bypass scenarios() implementation in the parent class
        return Model::scenarios();
    }

    /**
     * Creates data provider instance with search query applied
     *
     * @param array $params
     *
     * @return ActiveDataProvider
     */
    public function search($params)
    {
        $query = vehicleseatreservation::find();

        // add conditions that should always apply here

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
        ]);

        $this->load($params);

        if (!$this->validate()) {
            // uncomment the following line if you do not want to return any records when validation fails
            // $query->where('0=1');
            return $dataProvider;
        }

        // grid filtering conditions
        $query->andFilterWhere([
            'vehicle_id' => $this->vehicle_id,
            'seat_reservation_id' => $this->seat_reservation_id,
            'user_id' => $this->user_id,
            'time_start' => $this->time_start,
            'time_end' => $this->time_end,
            'date' => $this->date,
        ]);

        $query->andFilterWhere(['like', 'remarks', $this->remarks]);

        return $dataProvider;
    }
}
