<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "seat".
 *
 * @property integer $id
 * @property string $seat_number
 *
 * @property VehicleSeatReservation[] $vehicleSeatReservations
 * @property Vehicle[] $vehicles
 */
class Seat extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'seat';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id'], 'required'],
            [['id'], 'integer'],
            [['seat_number'], 'string', 'max' => 45],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'seat_number' => 'Seat Number',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getVehicleSeatReservations()
    {
        return $this->hasMany(VehicleSeatReservation::className(), ['seat_reservation_id' => 'id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getVehicles()
    {
        return $this->hasMany(Vehicle::className(), ['id' => 'vehicle_id'])->viaTable('vehicle_seat_reservation', ['seat_reservation_id' => 'id']);
    }
}
