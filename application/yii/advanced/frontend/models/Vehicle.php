<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "vehicle".
 *
 * @property integer $id
 * @property string $type
 * @property string $plate_number
 * @property string $model
 * @property integer $route_id
 *
 * @property Route $route
 * @property VehicleSeatReservation[] $vehicleSeatReservations
 * @property Seat[] $seatReservations
 */
class Vehicle extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'vehicle';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'route_id'], 'required'],
            [['id', 'route_id'], 'integer'],
            [['type', 'plate_number', 'model'], 'string', 'max' => 45],
            [['route_id'], 'exist', 'skipOnError' => true, 'targetClass' => Route::className(), 'targetAttribute' => ['route_id' => 'id']],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'type' => 'Type',
            'plate_number' => 'Plate Number',
            'model' => 'Model',
            'route_id' => 'Route ID',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getRoute()
    {
        return $this->hasOne(Route::className(), ['id' => 'route_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getVehicleSeatReservations()
    {
        return $this->hasMany(VehicleSeatReservation::className(), ['vehicle_id' => 'id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getSeatReservations()
    {
        return $this->hasMany(Seat::className(), ['id' => 'seat_reservation_id'])->viaTable('vehicle_seat_reservation', ['vehicle_id' => 'id']);
    }
}
