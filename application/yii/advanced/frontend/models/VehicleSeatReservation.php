<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "vehicle_seat_reservation".
 *
 * @property integer $vehicle_id
 * @property integer $seat_reservation_id
 * @property integer $user_id
 * @property string $time_start
 * @property string $time_end
 * @property string $date
 * @property string $remarks
 *
 * @property Seat $seatReservation
 * @property Commuter $user
 * @property Vehicle $vehicle
 */
class VehicleSeatReservation extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'vehicle_seat_reservation';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['vehicle_id', 'seat_reservation_id', 'user_id'], 'required'],
            [['vehicle_id', 'seat_reservation_id', 'user_id'], 'integer'],
            [['time_start', 'time_end', 'date'], 'safe'],
            [['remarks'], 'string', 'max' => 45],
            [['seat_reservation_id'], 'exist', 'skipOnError' => true, 'targetClass' => Seat::className(), 'targetAttribute' => ['seat_reservation_id' => 'id']],
            [['user_id'], 'exist', 'skipOnError' => true, 'targetClass' => Commuter::className(), 'targetAttribute' => ['user_id' => 'id']],
            [['vehicle_id'], 'exist', 'skipOnError' => true, 'targetClass' => Vehicle::className(), 'targetAttribute' => ['vehicle_id' => 'id']],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'vehicle_id' => 'Vehicle ID',
            'seat_reservation_id' => 'Seat Reservation ID',
            'user_id' => 'User ID',
            'time_start' => 'Time Start',
            'time_end' => 'Time End',
            'date' => 'Date',
            'remarks' => 'Remarks',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getSeatReservation()
    {
        return $this->hasOne(Seat::className(), ['id' => 'seat_reservation_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUser()
    {
        return $this->hasOne(Commuter::className(), ['id' => 'user_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getVehicle()
    {
        return $this->hasOne(Vehicle::className(), ['id' => 'vehicle_id']);
    }
}
