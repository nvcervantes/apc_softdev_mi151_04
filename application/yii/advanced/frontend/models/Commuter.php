<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "commuter".
 *
 * @property integer $id
 * @property string $username
 * @property string $authkey
 * @property string $password_hash
 * @property string $password_reset_token
 * @property string $email
 * @property string $status
 * @property string $created_at
 * @property string $updated_at
 * @property integer $route_id
 *
 * @property Route $route
 * @property Poll[] $polls
 * @property VehicleSeatReservation[] $vehicleSeatReservations
 */
class Commuter extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'commuter';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'route_id'], 'required'],
            [['id', 'route_id'], 'integer'],
            [['created_at', 'updated_at'], 'safe'],
            [['username', 'authkey', 'password_hash', 'password_reset_token', 'email', 'status'], 'string', 'max' => 45],
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
            'username' => 'Username',
            'authkey' => 'Authkey',
            'password_hash' => 'Password Hash',
            'password_reset_token' => 'Password Reset Token',
            'email' => 'Email',
            'status' => 'Status',
            'created_at' => 'Created At',
            'updated_at' => 'Updated At',
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
    public function getPolls()
    {
        return $this->hasMany(Poll::className(), ['user_id' => 'id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getVehicleSeatReservations()
    {
        return $this->hasMany(VehicleSeatReservation::className(), ['user_id' => 'id']);
    }
}
