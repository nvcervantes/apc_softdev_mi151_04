<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "route".
 *
 * @property integer $id
 * @property string $origin
 * @property string $drop_off
 * @property string $eta
 * @property string $date
 *
 * @property Commuter[] $commuters
 * @property Rating[] $ratings
 * @property Vehicle[] $vehicles
 */
class Route extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'route';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id'], 'required'],
            [['id'], 'integer'],
            [['origin', 'drop_off', 'eta', 'date'], 'string', 'max' => 45],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'origin' => 'Origin',
            'drop_off' => 'Drop Off',
            'eta' => 'Eta',
            'date' => 'Date',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getCommuters()
    {
        return $this->hasMany(Commuter::className(), ['route_id' => 'id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getRatings()
    {
        return $this->hasMany(Rating::className(), ['route_id' => 'id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getVehicles()
    {
        return $this->hasMany(Vehicle::className(), ['route_id' => 'id']);
    }
}
